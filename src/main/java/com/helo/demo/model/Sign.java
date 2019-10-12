package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author xiayj
 * @since
 */
@Data
@TableName(value = "sign")
public class Sign {
    @TableId(value = "sign_id",type = IdType.AUTO)
    private Integer signId;

    private String signLocation;

    private Date signTime;

    private Integer studentId;

    private Integer courseId;

}