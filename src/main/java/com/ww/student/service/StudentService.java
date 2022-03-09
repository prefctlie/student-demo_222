package com.ww.student.service;

import com.ww.student.bean.StudentBean;

import java.util.List;

/**
 * @author DELL
 */
public interface StudentService {

    void saveStudent(StudentBean studentBean);

    Boolean deleteStudentById(String studentId);

    Boolean updateStudent(StudentBean studentBean);

    List<StudentBean> findByCondition(Integer currentPage, Integer pageSize, String condition);

    StudentBean findStudentById(String studentId);

}
