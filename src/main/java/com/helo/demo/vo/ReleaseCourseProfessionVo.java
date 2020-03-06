package com.helo.demo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReleaseCourseProfessionVo {

    private String courseName;

    private Date startTime;

    private Date endTime;

    private String professionName;

}
