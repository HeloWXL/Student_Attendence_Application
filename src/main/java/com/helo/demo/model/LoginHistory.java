package com.helo.demo.model;

import lombok.Data;

import java.util.Date;


@Data
public class LoginHistory {
    private Integer historyId;

    private Date createTime;

    private Integer loginId;

    private String browerName;

    private String computerVersion;

    private String ip;

}