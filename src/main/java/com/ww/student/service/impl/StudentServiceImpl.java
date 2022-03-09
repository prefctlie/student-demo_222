package com.ww.student.service.impl;

import com.ww.student.bean.StudentBean;
import com.ww.student.domain.Student;
import com.ww.student.enums.ErrorCodeEnum;
import com.ww.student.helper.DataBuilderHelper;
import com.ww.student.mapper.StudentMapper;
import com.ww.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author DELL
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void saveStudent(StudentBean studentBean) {
        Student studentByConverse = DataBuilderHelper.toStudent(studentBean);
        /**
         * 先在数据库里面判断是否存在该数据，不存在，就保存
         */
        Student studentByQuery = studentMapper.findById(studentBean.getStudentId());
        if (Objects.nonNull(studentByQuery)) {
            ErrorCodeEnum.ERROR_10012.throwException();
        }
        studentMapper.saveStudent(DataBuilderHelper.toStudent(studentBean));
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean deleteStudentById(String studentId) {
        Student student = studentMapper.findById(studentId);
        if (Objects.isNull(student)) {
            ErrorCodeEnum.ERROR_10010.throwException();
        }
        return studentMapper.deleteById(studentId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean updateStudent(StudentBean studentBean) {
        Student student = studentMapper.findById(studentBean.getStudentId());
        if (Objects.isNull(student)) {
            ErrorCodeEnum.ERROR_10010.throwException();
        }
        return studentMapper.updateStudent(DataBuilderHelper.toStudent(studentBean));
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentBean> findByCondition(Integer currentPage, Integer pageSize, String condition) {
        List<Student> students = studentMapper.selectLike(pageSize * (currentPage - 1), pageSize, condition);
        return students.stream().map(DataBuilderHelper::toStudentBean).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public StudentBean findStudentById(String studentId) {
        Student student = studentMapper.findById(studentId);
        if (Objects.isNull(student)) {
            ErrorCodeEnum.ERROR_10010.throwException();
        }
        return DataBuilderHelper.toStudentBean(student);
    }


}
