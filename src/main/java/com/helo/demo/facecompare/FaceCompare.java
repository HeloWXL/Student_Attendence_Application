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
    public static final String API_KEY = "L_mT-oglbWdX2uw20G3tGl5TJqBy9v3L";
    /**
     * API 的 API_SECRET
     */
    public static final String API_SECRET = "jaoj6KDENsq7JcgUL8mD-tMQVSMZRFlX";

    public static final String IMG_PATH =
            "D:\\wangxianlin\\facedemo\\src\\main\\resources\\images\\Goal.png";
    public static final String IMG_PATH_TO_COMPARE =
            "D:\\wangxianlin\\facedemo\\src\\main\\resources\\images\\toCompare.png";


//    public static void main(String[] args) {
//        File file1 = new File(IMG_PATH);
//        byte[] buff1 = getBytesFromFile(file1);
//        File file2 = new File(IMG_PATH_TO_COMPARE);
//        byte[] buff2 = getBytesFromFile(file2);
//        /**
//         * Url
//         * 必须要传递的参数
//         */
//        String url = "https://api-cn.faceplusplus.com/facepp/v3/compare";
//        /**
//         * 存放 api_key ， api_secret
//         */
//        HashMap<String, String> map = new HashMap<>();
//        /**
//         * 存放 image_file 因为它是一个二进制数组
//         */
//        HashMap<String, byte[]> byteMap = new HashMap<>();
//        map.put("api_key", API_KEY);
//        map.put("api_secret", API_SECRET);
//        /***
//         * image_file
//         * 一个图片，二进制文件，需要用 post multipart/form-data 的方式上传
//         */
//        byteMap.put("image_file1", buff1);
//        byteMap.put("image_file2", buff2);
//        try {
//            byte[] bacd = post(url, map, byteMap);
//            String str = new String(bacd);
//            System.out.println(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
