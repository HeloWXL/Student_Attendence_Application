package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
@TableName(value = "API")
public class API {

    @TableId(value = "API_ID",type = IdType.AUTO)
    private Integer apiId;

    /**
     * Api接口地址
     */
    private String apiUrl;

    private String apiKey;

    /**
     * Api密钥
     */
    private String apiSecret;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date creareTime;
    /**
     * 创建人
     */
    private Integer counsrlorId;
    /**
     * 启用状态
     */
    private Integer state;

    @TableField(exist = false)
    private String counselorName;

}