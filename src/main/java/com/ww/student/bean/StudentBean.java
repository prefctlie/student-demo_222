package com.ww.student.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DELL
 */
@Getter
@Setter
public class StudentBean {
    private String studentId;
    private String name;
    private Integer age;
    private Integer gender;
    private String createTime;
    private String updateTime;
    private String clazz;
}
