package com.ww.student.controller;

import com.ww.student.bean.StudentBean;
import com.ww.student.common.ResultBean;
import com.ww.student.enums.ErrorCodeEnum;
import com.ww.student.helper.StudentBeanCacheHelper;
import com.ww.student.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author DELL
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 添加学生
     *
     * @param studentBean 学生参数
     * @return 添加结果
     */
    @PostMapping("/save")
    public ResultBean<String> addStudent(@RequestBody StudentBean studentBean) {
        //校验参数
        ResultBean<String> resultBean = checkParams(studentBean);
        if (ResultBean.hasError(resultBean)) {
            return resultBean;
        }
        //保存至数据库
        studentService.saveStudent(studentBean);
        //缓存数据
        StudentBeanCacheHelper.getInstance().save(studentBean);
        return ResultBean.SUCCESS();
    }

    private ResultBean<String> checkParams(StudentBean studentBean) {
        if (Objects.isNull(studentBean)) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10001.getCode(), ErrorCodeEnum.ERROR_10001.getMsg());
        }
        //对学号进行校验
        String studentId = studentBean.getStudentId();
        if (StringUtils.isEmpty(studentId) || studentId.length() > 11) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10002.getCode(), ErrorCodeEnum.ERROR_10002.getMsg());
        }
        //对名字进行校验
        String name = studentBean.getName();
        if (StringUtils.isEmpty(name) || name.length() > 32) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10003.getCode(), ErrorCodeEnum.ERROR_10003.getMsg());
        }
        //对age进行校验
        Integer age = studentBean.getAge();
        if (Objects.isNull(age) || age < 0 || age > 99) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10004.getCode(), ErrorCodeEnum.ERROR_10004.getMsg());
        }
        //对性别进行参数校验
        Integer gender = studentBean.getGender();
        if (Objects.isNull(gender) || gender < 0 || gender > 1) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10005.getCode(), ErrorCodeEnum.ERROR_10005.getMsg());
        }
        //对班级进行校验
        String clazz = studentBean.getClazz();
        if (StringUtils.isEmpty(clazz) || clazz.length() > 64) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10006.getCode(), ErrorCodeEnum.ERROR_10006.getMsg());
        }
        return ResultBean.SUCCESS();
    }

    /**
     * 根据学号删除学生
     *
     * @param studentId 学生id
     * @return 删除成功, 返回删除成功的信息, 删除失败, 返回删除失败的信息
     */
    @DeleteMapping("delete/{studentId}")
    public ResultBean<String> delete(@PathVariable("studentId") String studentId) {
        Boolean isSuccess = studentService.deleteStudentById(studentId);
        if (!isSuccess) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10009.getCode(), ErrorCodeEnum.ERROR_10009.getMsg());
        }
        StudentBeanCacheHelper.getInstance().remove(studentId);
        return ResultBean.SUCCESS();
    }

    /**
     * 根据studentId对一个学生信息进行更新操作
     *
     * @param studentBean 是一个studentBean对象
     * @return 返回相应信息
     */
    @PutMapping("update")
    public ResultBean<String> update(@RequestBody StudentBean studentBean) {
        //校验参数
        ResultBean<String> resultBean = checkParams(studentBean);
        if (ResultBean.hasError(resultBean)) {
            return resultBean;
        }
        Boolean isSuccess = studentService.updateStudent(studentBean);
        if (!isSuccess) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10008.getCode(), ErrorCodeEnum.ERROR_10008.getMsg());
        }
        StudentBeanCacheHelper.getInstance().save(studentBean);
        return ResultBean.SUCCESS();
    }

    /**
     * 分页模糊查询
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param condition   查询条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResultBean<List<StudentBean>> selectCondition(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(value = "condition", required = false) String condition) {
        //升序
        List<StudentBean> studentBeansAscend = studentService.findByCondition(currentPage, pageSize, condition).stream()
            .sorted(Comparator.comparing(StudentBean::getAge)).collect(Collectors.toList());
        //降序
        List<StudentBean> studentBeanListDescend = studentService.findByCondition(currentPage, pageSize, condition).stream()
            .sorted(Comparator.comparing(StudentBean::getAge).reversed()).collect(Collectors.toList());
        return ResultBean.SUCCESS(studentBeanListDescend);
    }

    /**
     * 根据id进行精确查询,优先在redis里面进行查找
     *
     * @param studentId 学生学号
     * @return 查询结果
     */
    @GetMapping("/detail/{studentId}")
    public ResultBean<StudentBean> findById(@PathVariable String studentId) {
        if (StringUtils.isEmpty(studentId) || studentId.length() > 11) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10011.getCode(), ErrorCodeEnum.ERROR_10011.getMsg());
        }
        StudentBean studentBean = StudentBeanCacheHelper.getInstance().get(studentId);
        if (Objects.nonNull(studentBean)) {
            return ResultBean.SUCCESS(studentBean);
        }
        StudentBean studentBeanDB = studentService.findStudentById(studentId);
        if (Objects.isNull(studentBeanDB)) {
            return ResultBean.FAIL(ErrorCodeEnum.ERROR_10010.getCode(), ErrorCodeEnum.ERROR_10010.getMsg());
        }
        StudentBeanCacheHelper.getInstance().save(studentBeanDB);
        return ResultBean.SUCCESS(studentBeanDB);
    }
}


