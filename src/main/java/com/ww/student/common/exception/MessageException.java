package com.ww.student.common.exception;

import com.ww.student.enums.ErrorCodeEnum;

/**
 *
 * @author DELL
 */
public class MessageException extends RuntimeException {
    private int code;

    public MessageException(String message) {
        super(message);
        this.code = 1;
    }

    public MessageException(int code, String message) {
        this(message);
        this.code = code;
    }

    public MessageException(ErrorCodeEnum msg) {
        this(msg.getCode(), msg.getMsg());
    }

    public int getCode() {
        return this.code;
    }
}