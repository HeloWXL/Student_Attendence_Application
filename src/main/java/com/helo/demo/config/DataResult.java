package com.helo.demo.config;

/**
 * @author wangxl
 * @ClassName DataResult
 * @Description
 * @date 2019/8/21 14:32
 */
public class DataResult<T> {
  private String code;
  private String msg;
  private T body;


  /**
   * 默认处理成功,无需传入Code,Desc
   * @param body
   */
  public DataResult(T body){
    this.code = PlatStateCode.getByState("200").getCode();
    this.msg = PlatStateCode.getByState("CD000001").getDesc();
    this.body = body;
  }

  public DataResult(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public DataResult(PlatStateCode platStateCode){
    this.code = platStateCode.getCode();
    this.msg = platStateCode.getDesc();
  }

  public DataResult(){
    this.code =  PlatStateCode.getByState("200").getCode();
    this.msg = PlatStateCode.getByState("200").getDesc();
  }

  public DataResult(PlatStateCode platStateCode, T body){
    this.code = platStateCode.getCode();
    this.msg = platStateCode.getDesc();
    this.body = body;
  }

  public DataResult(PlatExceptionCode platExceptionCode, T body){
    this.code = platExceptionCode.getCode();
    this.msg = platExceptionCode.getDesc();
    this.body = body;
  }

  @Override
  public String toString() {
    return "ResultData{" +
        "code=" + code +
        ", body=" + body +
        ", msg='" + msg + '\'' +
        '}';
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getBody() {
    return body;
  }

  public void setBody(T body) {
    this.body = body;
  }
}
