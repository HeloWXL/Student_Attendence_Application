package com.helo.demo.config;

/**
 * @author wangxl
 * @ClassName PlatStateCode
 * @Description
 * @date 2019/8/21 14:33
 */
public enum   PlatStateCode {
  /** 处理成功 */
  SUCCESS("200", "success"),
  /** 处理中 */
  PROCESSING("CD000002", "doing"),
  /** 处理失败 */
  FAILURE("500", "error"),
  ;

  private final String code;
  private final String desc;

  private PlatStateCode(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static PlatStateCode getByState(String state) {
    if (state == null || state.length() == 0) {
      return null;
    }
    for (PlatStateCode each : values()) {
      if (state.equals(each.getCode())) {
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
