package com.helo.demo.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Release)实体类
 *
 * @author makejava
 * @since 2020-03-05 23:35:37
 */
@Data
@TableName("appdemo.release")
public class Release implements Serializable {
    private static final long serialVersionUID = -10780072986710328L;

    @TableId(value = "release_id",type = IdType.AUTO)
    private Integer releaseId;
    
    private Integer teacherId;
    
    private Date startTime;
    
    private Date endTime;
    
    private Integer professionId;
    
    private Integer courseId;

}