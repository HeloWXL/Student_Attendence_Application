package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.helo.demo.mapper.CounselorMapper;
import com.helo.demo.model.Counselor;
import com.helo.demo.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wangxl
 * @ClassName CounselorService
 * @Description
 * @date 2019/8/21 0:02
 */
@Service
public class CounselorService {

  @Resource
  private CounselorMapper counselorMapper;

  /**
   * 根据辅导员的ID删除辅导员信息
   * @param counselorId
   * @return
   */
  public  int deleteByPrimaryKey(Integer counselorId){
    return counselorMapper.deleteByPrimaryKey(counselorId);
  }

  /**
   * 添加辅导员信息
   * @param record
   * @return
   */
  public int insertSelective(Counselor record){
    record.setCounselorPassword(Md5Utils.getSaltMD5(record.getCounselorPassword()));
    return counselorMapper.insertSelective(record);
  }

  /**
   * 根据ID查询辅导员信息
   * @param counselorId
   * @return
   */
  public  Counselor selectByPrimaryKey(Integer counselorId){
    return counselorMapper.selectByPrimaryKey(counselorId);
  }

  /**
   *  修改辅导员信息
   * @param record
   * @return
   */
  public int updateByPrimaryKeySelective(Counselor record){
    return counselorMapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 获取辅导员列表分页
   * @param pageNo
   * @param pageSize
   * @return
   */
  public Map<String, Object> getCounselorByPage(Integer pageNo, Integer pageSize) {
    EntityWrapper entityWrapper = new EntityWrapper();
    List<Counselor> counselors = counselorMapper.selectPage(new Page<Counselor>(pageNo,pageSize),entityWrapper);
    int count = counselorMapper.selectCount(entityWrapper);
    Map<String,Object> map = new HashMap<>();
    map.put("list",counselors);
    map.put("count",count);
    return map;
  }

  /**
   * 根据辅导员工号查询辅导员信息
   * @param cno
   * @return
   */
  public Counselor selectByCno(String cno){
    return counselorMapper.selectByCno(cno);
  }
}
