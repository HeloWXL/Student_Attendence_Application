package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author wangxl
 * @since
 */
@Data
@TableName(value = "sign")
public class Sign {

    @TableId(value = "sign_id",type = IdType.AUTO)
    private Integer signId;

    private String signLocation;

    private String signOutLocation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private Integer studentId;

    private Integer courseId;
    /**
     *  1表示已签到 0表示为签到
     */
    private Integer isStartStatus;
    /**
     * 1表示已签退 0表示为未签退
     */
    private Integer isEndStatus;
    /**
     * 发布ID
    */
    @TableField("release_id")
    private Integer ReleaseId;

    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private String studentName;

}