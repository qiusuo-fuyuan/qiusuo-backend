package qiusuo.platform.exception;

import qiusuo.platform.utils.string.QiuSuoStringUtils;

public class QiuSuoException extends Exception {
    public QiuSuoException(String pattern, Object... args) {
        super(QiuSuoStringUtils.message(pattern, args));
    }
}
