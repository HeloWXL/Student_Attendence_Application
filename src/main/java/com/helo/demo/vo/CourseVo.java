package com.helo.demo.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Classname CourseVo
 * @Description TODO
 * @Date 2019/11/6 1:01 上午
 * @Created by wangxianlin
 */

@Data
public class CourseVo {

    private Integer courseId;

    private String courseName;

    private Integer classarrangement;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date starttime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endtime;

//专业相关信息
    private String professionName;

    private String apartment;

    private String school;
//教师相关信息
    private String teacherName;

    private String teacherJobTitle;



}
