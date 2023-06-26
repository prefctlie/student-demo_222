package com.ww.student.aop.mdc;

import org.slf4j.MDC;

/**
 * @author To_Be_enlightenment
 * @version 1.0
 * @project student-demo
 * @description  MDC工具类
 * @date 2023/6/24 00:18:16
 */
public class MdcUtils {
    public static final String TRACE_ID_KEY = "traceId";

    public static void add(String key, String val) {
        MDC.put(key, val);
    }

    public static void addTraceId(String value){
        MDC.put(TRACE_ID_KEY,value);
    }

    public static String getTraceId(){
        return MDC.get(TRACE_ID_KEY);
    }


    public static void reset(){
        String traceId = MDC.get(TRACE_ID_KEY);
        MDC.put(TRACE_ID_KEY,traceId);
        MDC.clear();
    }

    public static void clear(){
        MDC.clear();
    }




}
