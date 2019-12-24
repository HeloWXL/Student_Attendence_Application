package com.helo.demo.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Classname SignStudentVo
 * @Description TODO
 * @Date 2019/12/23 10:53 下午
 * @Created by wangxianlin
 */
@Data
public class SignStudentVo {
    @TableId(value = "sign_id",type = IdType.AUTO)
    private Integer signId;

    private String signLocation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private Integer studentId;

    private Integer courseId;

    private Integer isStartStatus;

    private Integer isEndStatus;

    private String studentName;
}
