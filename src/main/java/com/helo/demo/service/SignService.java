package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.helo.demo.mapper.SignMapper;
import com.helo.demo.model.Sign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName SignService
 * @Description TODO
 * @date 2019/8/23 17:50
 * @Version 1.0
 */
@Service
public class SignService {

  @Resource
  private SignMapper signMapper;

  /**
   * 添加签到记录
   * @param sign
   * @return
   */
  public int insertSign(Sign sign){
    return signMapper.insertSelective(sign);
  }


  public Sign selectSignById(Integer signId){
    return signMapper.selectById(signId);
  }


  /**
   * 获取签到列表分页
   * @param pageNo
   * @param pageSize
   * @return
   */
  public Map<String, Object> getSignByPage(Integer pageNo, Integer pageSize) {
    EntityWrapper entityWrapper = new EntityWrapper();
    List<Sign> signList = signMapper.selectPage(new Page<Sign>(pageNo,pageSize),entityWrapper);
    int count = signMapper.selectCount(entityWrapper);
    Map<String,Object> map = new HashMap<>();
    map.put("data",signList);
    map.put("count",count);
    map.put("code",0);
    map.put("msg","");
    return map;
  }

  public Map<String, Object> getSignStuByPage(Integer pageNo, Integer pageSize,Integer stuId) {
    EntityWrapper entityWrapper = new EntityWrapper();
    entityWrapper.eq("student_id",stuId);
    List<Sign> signList = signMapper.selectPage(new Page<Sign>(pageNo,pageSize),entityWrapper);
    int count = signMapper.selectCount(entityWrapper);
    Map<String,Object> map = new HashMap<>();
    map.put("data",signList);
    map.put("count",count);
    return map;
  }

}
