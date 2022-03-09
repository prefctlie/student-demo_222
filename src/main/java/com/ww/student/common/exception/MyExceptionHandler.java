package com.ww.student.common.exception;

import com.ww.student.common.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获处理
 *
 * @author DELL
 * @ControllerAdvice： 异常集中处理，更好的使业务逻辑与异常处理剥离开；其是对Controller层进行拦截
 */
@ControllerAdvice
public class MyExceptionHandler {
    /**
     * 统一异常处理
     *
     * @ExceptionHandler： 统一处理某一类异常，从而能够减少代码重复率和复杂度
     * @ResponseStatus： 可以将某种异常映射为HTTP状态码
     * @ResponseBody : @ResponseBody的作用其实是将java对象转为json格式的数据。
     * @responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
     * 写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
     * 注意：在使用此注解之后不会再走视图处理器，而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。
     * 表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用【也就是AJAX】。 注意：在使用
     *  @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。 比如异步获取 json 数据，加上
     * 后，会直接返回 json 数据。@RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultBean<String> errorHandler(Exception e) {
        e.printStackTrace();
        return ResultBean.FAIL(1, "捕获到异常:" + e.getMessage());
    }

    /**
     * 自定义异常的处理
     */
    @ResponseBody
    @ExceptionHandler(value = MessageException.class)
    public ResultBean<String> myErrorHandler(MessageException e) {
        return ResultBean.FAIL(e.getCode(), e.getMessage());
    }

}
