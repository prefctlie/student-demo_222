package com.ww.student.util;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author DELL
 */
public class TimeUtil {

    private static final long SECONDS_TIME_MAX = 9999999999L;

    private static final String FORMAT_FULL_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间戳转换为yyyy-MM-dd HH:mm:ss格式
     */
    public static String parseSecondsToDate(Long time) {
        time = getMillisSecond(time);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_FULL_TIME);
        return sdf.format(time);
    }

    /**
     * 获取当前
     */
    public static Long nowSecond() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 秒或毫秒均转化为秒
     */
    public static Long getMillisSecond(Long time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return time < SECONDS_TIME_MAX ? time * 1000 : time;
    }

}
