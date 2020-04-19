package com.helo.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2020-04-19 10:16:33
 */
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = -92777839790031731L;
    
    private Integer id;
    
    private String name;
    
    private String password;
    
    private Date createTime;
    
    private String nickName;
}