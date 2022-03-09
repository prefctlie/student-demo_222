package com.ww.student.mapper;

import com.ww.student.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DELL
 */
@Mapper
public interface StudentMapper {
    Boolean saveStudent(Student student);

    List<Student> selectLike(@Param("currentPage") Integer currentPage,
                             @Param("pageSize") Integer pageSize,
                             @Param("condition") String condition);

    Boolean deleteById(String studentId);

    Boolean updateStudent(Student student);

    Student findById(String studentId);

}
