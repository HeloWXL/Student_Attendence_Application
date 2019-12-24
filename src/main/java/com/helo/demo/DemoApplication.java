package com.helo.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 192.168.222.39
 * 学生
 * 127.0.0.1:8081/helo/studentApi/toLogin
 * 教师
 * 127.0.0.1:8081/helo/teacherApi/toLogin
 * 辅导员
 * 127.0.0.1:8081/helo/counselorApi/toCounselorLogin
 */
@MapperScan("com.helo.demo.mapper")
@EnableSwagger2
@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}


