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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    private Integer studentId;

    private Integer courseId;

    private Integer isStartStatus;

    private Integer isEndStatus;

    @TableField(exist = false)
    private String studentName;

}