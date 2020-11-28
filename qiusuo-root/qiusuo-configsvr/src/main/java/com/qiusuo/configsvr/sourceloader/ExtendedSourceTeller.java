package com.qiusuo.configsvr.sourceloader;

import java.util.List;

/**
 * Base interface of all extended source loader.
 * It is used to tell config client the source information.
 * E.g. if you have the resources of "auth.graphqls" and "channel.graphqls", you can return the key property
 * {"graphqls_list": "auth.graphqls,channel.graphqls"}.
 *
 */
public interface ExtendedSourceTeller {
    /**
     * Provides a name of the key property that the config client can recognize.
     *
     * @return  the name of the property.
     */
    public String getKeyPropertyName();

    /**
     * Provides a value of the key property to tell the config client what resources are available.
     *
     * @param resourceNameList
     *      the list of the resource name.
     *
     * @return the value of the property which tells the config client what resources are available.
     */
    public String getKeyPropertyValue(List<String> resourceNameList);
}
