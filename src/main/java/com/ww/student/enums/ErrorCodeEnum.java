package com.ww.student.enums;

import com.ww.student.common.exception.MessageException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Zhanglele
 * @version 2021/7/29 10:20
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * 10001 代表参数有误
     */
    ERROR_10001(10001, "参数有误"),
    ERROR_10002(10002, "学号不能为空或者为空串或者长度大于11"),
    ERROR_10003(10003, "姓名长度只能在0~32之间"),
    ERROR_10004(10004, "年龄在0~99之间"),
    ERROR_10005(10005, "性别0代表女生,1代表男生"),
    ERROR_10006(10006, "班级的长度不能大于64"),
    ERROR_10007(10007, "保存失败"),
    ERROR_10008(10008, "更新失败"),
    ERROR_10009(10009, "删除失败"),
    ERROR_10010(10010, "学生不存在"),
    ERROR_10011(10011, "请输入正确的学号"),
    ERROR_10012(10012, "此学号已存在"),
    ;
    private final int code;
    private final String msg;

    public void throwException(){
        throw new MessageException(this.getCode(), this.getMsg());
    }
}
