package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 学生
 * @author wangxl
 * @since
 */
@Data
@TableName(value = "student")
public class Student {
    @TableId(value = "student_id",type = IdType.AUTO)
    private Integer studentId;

    @TableField("student_name")
    private String studentName;

    @TableField("student_sno")
    private String studentSno;

    @TableField("student_password")
    private String studentPassword;

    @TableField("student_age")
    private String studentAge;

    @TableField("student_qq")
    private String studentQq;

    @TableField("student_email")
    private String studentEmail;

    @TableField("student_pic")
    private String studentPic;

    @TableField("student_sex")
    private String studentSex;

    @TableField("profession_id")
    private Integer professionId;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    @TableField("img_base_64")
    private String imgBase64;

    @TableField(exist = false)
    private Profession professions;

    @TableField(exist = false)
    private List<Course> courseList ;

    @TableField(exist = false)
    private int page;

    @TableField(exist = false)
    private int limit;
}