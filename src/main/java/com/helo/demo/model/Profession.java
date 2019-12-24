package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 专业
 * @author wangxl
 * @since
 */
@Data
public class Profession {

    @TableId(value = "profession_id",type = IdType.AUTO)
    private Integer professionId;

    private String professionName;

    private String apartment;

    private String school;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

}