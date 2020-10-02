package com.qiusuo.communityservice.util.string;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class StringUtils {
    public static String message(String pattern, Object ...args) {
        StringFormattedMessage formattedMessage = new StringFormattedMessage(pattern, args);
        return formattedMessage.getFormattedMessage();
    }
}
