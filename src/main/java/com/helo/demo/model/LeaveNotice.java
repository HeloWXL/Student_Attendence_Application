package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (LeaveNotice)实体类
 *
 * @author makejava
 * @since 2020-03-28 17:15:37
 */
@Data
public class LeaveNotice implements Serializable {
    private static final long serialVersionUID = 399102466861272116L;
    /**
    * ID
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
    * 请假ID
    */
    @TableField("leave_id")
    private Integer leaveId;
    /**
    * 学生ID
    */
    @TableField("student_id")
    private Integer studentId;
    /**
    * 课程名
    */
    @TableField("course_name")
    private String courseName;
    /**
    * 是否已读 1 已读，0未读
    */
    @TableField("state")
    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("ceate_time")
    private Date ceateTime;

    public LeaveNotice(Integer leaveId, Integer studentId, String courseName, Integer state, Date ceateTime) {
        this.leaveId = leaveId;
        this.studentId = studentId;
        this.courseName = courseName;
        this.state = state;
        this.ceateTime = ceateTime;
    }

    public LeaveNotice() {
    }
}