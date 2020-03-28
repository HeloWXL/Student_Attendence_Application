package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author wangxl
 * @since
 */
@Data
@TableName(value = "leaves")
public class Leave {

    @TableId(value = "leave_id",type = IdType.AUTO)
    private Integer leaveId;

    @TableField("student_sno")
    private String studentSno;

    @TableField("leave_title")
    private String leaveTitle;

    @TableField("leave_reason")
    private String leaveReason;

    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date startTime;

    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date endTime;

    @TableField("coundelor_id")
    private Integer coundelorId;

    @TableField("is_read")
    private Integer isRead;

    private Student student;

    @TableField("course_id")
    private Integer courseId;

    @TableField(exist = false)
    private Course course;

    @TableField(exist = false)
    private String studentName;

    @TableField("file_url")
    private String fileUrl;
}