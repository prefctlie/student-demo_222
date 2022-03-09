package com.ww.student.helper;

import com.ww.student.bean.StudentBean;
import com.ww.student.domain.Student;
import com.ww.student.util.TimeUtil;

/**
 * @author DELL
 */
public class DataBuilderHelper {

    /**
     * 把Student转换成StudentBean
     */
    public static StudentBean toStudentBean(Student student) {
        StudentBean studentBean = new StudentBean();
        studentBean.setStudentId(student.getStudentId());
        studentBean.setAge(student.getAge());
        studentBean.setName(student.getName());
        studentBean.setGender(student.getGender());
        studentBean.setCreateTime(TimeUtil.parseSecondsToDate(student.getCreateTime()));
        studentBean.setUpdateTime(TimeUtil.parseSecondsToDate(student.getUpdateTime()));
        studentBean.setClazz(student.getClazz());
        return studentBean;
    }

    /***
     * 把StudentBean转换成Student
     */
    public static Student toStudent(StudentBean studentBean) {
        Student student = new Student();
        student.setCreateTime(TimeUtil.nowSecond());
        student.setUpdateTime(TimeUtil.nowSecond());
        student.setAge(studentBean.getAge());
        student.setClazz(studentBean.getClazz());
        student.setStudentId(studentBean.getStudentId());
        student.setGender(studentBean.getGender());
        student.setName(studentBean.getName());
        return student;
    }

}
