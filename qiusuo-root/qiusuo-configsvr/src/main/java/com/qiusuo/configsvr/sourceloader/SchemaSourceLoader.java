package com.qiusuo.configsvr.sourceloader;

import io.micrometer.core.instrument.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Strategy to load '.graphqls' files into a {@link PropertySource}.
 */

public class SchemaSourceLoader implements PropertySourceLoader, ExtendedSourceTeller {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaSourceLoader.class);

    private static final String FOLDER_SEPARATOR = "/";

    @Override
    public String[] getFileExtensions() {
        return new String[]{"graphqls"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        String schemaPath = null;
        if (resource instanceof ClassPathResource) {
            schemaPath = ((ClassPathResource) resource).getPath();
        } else if (resource instanceof FileUrlResource) {
            schemaPath = resource.getURL().getPath();
        } else {
            this.LOGGER.error("Something went wrong, resource is not instance of ClassPathResource or FileUrlResource.");
            return Collections.emptyList();
        }

        String schemaFolder = getResourceParentPath(schemaPath);
        List<String> schemaNameList = new ArrayList<>();
        List<String> schemaPathList = new ArrayList<>();
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            if (!line.equals("")) {
                schemaNameList.add(line);
                schemaPathList.add(schemaFolder + line);
            }
        }
        reader.close();

        Map<String, String> sources = new HashMap<String, String>();
        sources.put(getKeyPropertyName(), getKeyPropertyValue(schemaNameList));
        for (int i = 0; i < schemaPathList.size(); i++) {
            String schemaName = schemaNameList.get(i);
            String path = schemaPathList.get(i);
            Resource schemaResource = resource instanceof ClassPathResource ?
                    new ClassPathResource(path) : new FileUrlResource(path);
            String content = IOUtils.toString(schemaResource.getInputStream(), StandardCharsets.UTF_8);
            sources.put(schemaName, content);
        }

        List<Map<String, String>> loaded = new ArrayList<>();
        loaded.add(sources);

        List<PropertySource<?>> propertySources = new ArrayList<>(loaded.size());
        for (int i = 0; i < loaded.size(); i++) {
            String documentNumber = (loaded.size() != 1) ? " (document #" + i + ")" : "";
            propertySources.add(new OriginTrackedMapPropertySource(name + documentNumber,
                    Collections.unmodifiableMap(loaded.get(i)), true));
        }

        return propertySources;
    }

    /**
     * generate "graphqls_list" as property name.
     *
     * @return
     */
    @Override
    public String getKeyPropertyName() {
        return getFileExtensions()[0] + "_list";
    }

    @Override
    public String getKeyPropertyValue(List<String> resourceNameList) {
        if (resourceNameList == null || resourceNameList.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < resourceNameList.size(); i++) {
            builder.append(resourceNameList.get(i));
            if (i < resourceNameList.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    private static String getResourceParentPath(String path) {
        if (path == null) {
            return null;
        }

        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex == -1) {
            return "";
        }

        return path.substring(0, folderIndex + 1);
    }

}
