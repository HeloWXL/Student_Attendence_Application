package com.helo.demo.facecompare;

import java.io.File;
import java.util.HashMap;

import static com.helo.demo.facecompare.CommonMethod.getBytesFromFile;
import static com.helo.demo.facecompare.CommonMethod.post;

/**
 * @Classname FaceCompare
 * @Description TODO
 * @Date 2019/12/28 10:49 上午
 * @Created by wangxianlin
 */
public class FaceCompare {
    /**
     * API的API_KEY
     */
    private String apiKey;
    /**
     * API 的 API_SECRET
     */
    private String apiSecret;
    /**
     * Api 地址
     */
    private String url;
    /**
     * 第一张图片的Base64编码
     */
    private String base64ImageOne;
    /**
     * 第二张图片的Base64编码
     */
    private String base64ImageSecond;


    public FaceCompare(String apiKey, String apiSecret, String url, String base64ImageOne, String base64ImageSecond) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.url = url;
        this.base64ImageOne = base64ImageOne;
        this.base64ImageSecond = base64ImageSecond;
    }

    public String compareRes() {
        HashMap<String, String> map = new HashMap<>();
        /**
         * 存放 image_file 因为它是一个二进制数组
         */
        map.put("api_key", this.getApiKey());
        map.put("api_secret", this.getApiSecret());
        HashMap<String, String> baseMap = new HashMap<>();

        /***
         * image_file
         * 一个图片，二进制文件，需要用 post multipart/form-data 的方式上传
         */
        baseMap.put("image_base64_1", this.getBase64ImageOne());
        baseMap.put("image_base64_2", this.getBase64ImageSecond());
        String str = null;
        try {
            byte[] bacd = post(url, map, baseMap);
             str = new String(bacd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    public String getBase64ImageOne() {
        return base64ImageOne;
    }

    public void setBase64ImageOne(String base64ImageOne) {
        this.base64ImageOne = base64ImageOne;
    }

    public String getBase64ImageSecond() {
        return base64ImageSecond;
    }

    public void setBase64ImageSecond(String base64ImageSecond) {
        this.base64ImageSecond = base64ImageSecond;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * {
     * "faces1":[
     * {
     * "face_rectangle":{
     * "width":79,
     * "top":53,
     * "left":28,
     * "height":79
     * },
     * "face_token":"ac2a0e45801f71f305a69d5457c1fdd2"
     * }
     * ],
     * "faces2":[
     * {
     * "face_rectangle":{
     * "width":47,
     * "top":46,
     * "left":76,
     * "height":47
     * },
     * "face_token":"c1e21e719d98d3752f2931dc391b2c0c"
     * }
     * ],
     * "time_used":499,
     * "thresholds":{
     * "1e-3":62.327,
     * "1e-5":73.975,
     * "1e-4":69.101
     * },
     * "confidence":83.324,
     * "image_id2":"UuDIPlFB8a6zLU7NMCsMbA==",
     * "image_id1":"1FCyTJaK26tRfLMF26JL9Q==",
     * "request_id":"1567235504,fba193bd-b9fd-4a6f-b434-5242d2840f30"
     * }
     *
     *
     *
     */
}
