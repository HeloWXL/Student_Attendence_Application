package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author
 * @since
 */
@Data
public class Course {
    @TableId(value = "course_id",type = IdType.AUTO)
    private Integer courseId;

    @TableField("course_name")
    private String courseName;

    @TableField("classarrangement")
    private Integer classarrangement;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @TableField("startTime")
    private Date starttime;

    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @TableField("endTIme")
    private Date endtime;

    @TableField("profession")
    private Integer profession;

    @TableField("teacher_id")
    private Integer teacherId;

    @TableField("ceate_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ceateTime;

    @TableField(exist = false)
    private Teacher teacher;

    @TableField(exist = false)
    private Profession professions;
}