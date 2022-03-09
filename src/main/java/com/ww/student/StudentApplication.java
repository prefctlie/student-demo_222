package com.ww.student;


import com.ww.common.spring.utils.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 为什么要加mapperScan注解
 * @author DELL
 */
@SpringBootApplication
@Import({BeanFactory.class})
public class StudentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }
}
