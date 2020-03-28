package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 任课老师
 * @author wangxl
 * @since
 */
@Data
public class Teacher {

    @TableId(value = "teacher_id",type = IdType.AUTO)
    private Integer teacherId;

    private String teacherName;

    private String teacherTno;

    private String teacherPassword;

    private String teacherJobTitle;

    private String teacherSex;

    private Integer teacherAge;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS",timezone = "GMT+8")
    private Date createTime;

    private Integer professionId;

    @TableField(exist = false)
    private Profession profession;

    public Teacher(String teacherName, Integer professionId) {
        this.teacherName = teacherName;
        this.professionId = professionId;
    }

    public Teacher() {
    }
}