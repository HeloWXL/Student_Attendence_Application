package com.helo.demo.model;

import lombok.Data;

/**
 * @Classname FileMeta
 * @Description TODO
 * @Date 2019/12/4 9:28 下午
 * @Created by wangxianlin
 */
@Data
public class FileMeta {
    private long fileId;
    private String fileName;
    private String fileSize;
    private String fileType;
    private String filePath;
    private long downloadTimes;
}
