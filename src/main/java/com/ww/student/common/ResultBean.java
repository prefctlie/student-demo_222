package com.ww.student.common;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Zhanglele
 * @version 2021/7/29 10:22
 */
@Getter
@Setter
public class ResultBean<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResultBean<T> FAIL(int code, String msg) {
        ResultBean<T> bean = new ResultBean<>();
        bean.code = code;
        bean.message = msg;
        return bean;
    }

    public static <T> ResultBean<T> FAIL(String msg) {
        return FAIL(1, msg);
    }

    public static <T> ResultBean<T> SUCCESS(T t) {
        ResultBean<T> bean = new ResultBean<>();
        bean.code = 0;
        bean.setData(t);
        return bean;
    }

    public static <T> ResultBean<T> SUCCESS() {
        return SUCCESS(null);
    }

    public static boolean hasError(ResultBean<?> bean) {
        return bean != null && bean.code != 0;
    }

}
