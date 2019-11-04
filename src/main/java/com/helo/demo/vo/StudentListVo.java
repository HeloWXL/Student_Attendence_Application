package com.helo.demo.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Classname StudentListVo
 * @Description TODO
 * @Date 2019/11/4 8:46 下午
 * @Created by wangxianlin
 */
@Data
public class StudentListVo {

    private Integer studentId;

    private String studentName;

    private String studentSno;

    private String studentAge;

    private String studentQq;

    private String studentEmail;

    private String studentPic;

    private String studentSex;

    private Integer professionId;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    private String professionName;

    private String apartment;

    private String school;
}
