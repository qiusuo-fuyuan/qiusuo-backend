package qiusuo.platform.utils.string;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class QiuSuoStringUtils {
    public static String message(String pattern, Object... args) {
        StringFormattedMessage formattedMessage = new StringFormattedMessage(pattern, args);
        return formattedMessage.getFormattedMessage();
    }
}
