package com.ww.student.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ww.common.spring.utils.factory.BeanFactory;
import com.ww.student.bean.StudentBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 学生缓存工具类
 *
 * @author Zhanglele
 * @version 2021/7/29 11:02
 */
public class StudentBeanCacheHelper {

    private final static String STUDENT_BEAN_CACHE_PREFIX = "student:";

    private final StringRedisTemplate stringRedisTemplate;

    private static class StudentBeanCacheHelperHolder {
        private static final StudentBeanCacheHelper INSTANCE = new StudentBeanCacheHelper();
    }

    private StudentBeanCacheHelper() {
        stringRedisTemplate = BeanFactory.getBean(StringRedisTemplate.class);
    }

    public static StudentBeanCacheHelper getInstance() {
        return StudentBeanCacheHelperHolder.INSTANCE;
    }

    /**
     * 添加缓存
     */
    public void save(StudentBean studentBean) {
        stringRedisTemplate.opsForValue().set(STUDENT_BEAN_CACHE_PREFIX + studentBean.getStudentId(),
            JSONObject.toJSONString(studentBean), 20, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     */
    public void remove(String studentId) {
        stringRedisTemplate.delete(STUDENT_BEAN_CACHE_PREFIX + studentId);
    }

    /**
     * 获取缓存
     */
    public StudentBean get(String studentId) {
        String studentBeanStr = stringRedisTemplate.opsForValue().get(STUDENT_BEAN_CACHE_PREFIX + studentId);
        if (StringUtils.isEmpty(studentBeanStr)) {
            return null;

        }
        return JSON.parseObject(studentBeanStr, StudentBean.class);
    }

}
