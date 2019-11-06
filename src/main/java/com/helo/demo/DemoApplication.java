package com.helo.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 192.168.222.39
 * 学生
 * 127.0.0.1:8088/helo/studentApi/toLogin
 * 教师
 * 127.0.0.1:8088/helo/teacherApi/toLogin
 * 辅导员
 * 127.0.0.1:8088/helo/counselorApi/toCounselorIndex
 */
@MapperScan("com.helo.demo.mapper")
@EnableSwagger2
@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}


