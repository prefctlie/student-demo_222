package com.ww.student.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * @author DELL
 */
@Getter
@Setter
@Table(name = "student")
public class Student {
    private String studentId;
    private String name;
    private Integer age;
    private Integer gender;
    private Long createTime;
    private Long updateTime;
    private String clazz;
}
