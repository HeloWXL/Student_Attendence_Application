package com.helo.demo.config;

/**
 * @author wangxl
 * @ClassName PlatExceptionCode
 * @Description
 * @date 2019/8/21 14:33
 */
public enum PlatExceptionCode {
  /** 通用方法报错信息 */
  FAILURE_REQ_URL("CD001001", "无效请求地址", PlatStateCode.FAILURE),
  ERR_OPER_TYPE("CD001002", "无效操作类型", PlatStateCode.FAILURE),
  FAILURE_REQ_PRM_NULL("CD001003", "请求参数不能为空", PlatStateCode.FAILURE),
  FAILURE_REQ_PRM_ERR("CD001004", "请求参数异常，请重试", PlatStateCode.FAILURE),
  FAILURE_REQ_LOGIN_OUT("CD001005", "登录超时，请重新登录", PlatStateCode.FAILURE),
  FAILURE_REQ_NOT_LOGIN("CD001006", "未登录，请登录操作", PlatStateCode.FAILURE),
  FAILURE_REQ_ERR("CD001007", "非法请求", PlatStateCode.FAILURE),
  ;

  private final String code;
  private final String desc;
  private final PlatStateCode state;

  PlatExceptionCode(String code, String desc, PlatStateCode state) {
    this.code = code;
    this.desc = desc;
    this.state = state;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public PlatStateCode getState() {
    return state;
  }

  public static PlatExceptionCode getByCode(String resCode) {
    if (resCode == null || resCode.length() == 0) {
      return null;
    }
    for (PlatExceptionCode each : values()) {
      if (each.getCode().equals(resCode)) {
        return each;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "[" + code + ":" + desc + "]";
  }
}
