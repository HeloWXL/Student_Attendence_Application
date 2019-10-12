package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 辅导员
 * @author xiayj
 * @since
 */
@Data
public class Counselor {
    @TableId(value = "counselor_id",type = IdType.AUTO)
    private Integer counselorId;

    private String counselorName;

    private String counselorPassword;

    private Integer professionId;

    private Date createTime;

    private String counselorCno;

    @TableField(exist = false)
    private Profession profession;
}