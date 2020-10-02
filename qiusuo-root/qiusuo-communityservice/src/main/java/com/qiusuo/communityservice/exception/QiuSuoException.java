package com.qiusuo.communityservice.exception;

import com.qiusuo.communityservice.util.string.StringUtils;

public class QiuSuoException extends Exception {
    public QiuSuoException(String pattern, Object ...args) {
        super(StringUtils.message(pattern, args));
    }
}
