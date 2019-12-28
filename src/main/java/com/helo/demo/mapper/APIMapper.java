package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.API;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface APIMapper extends BaseMapper<API> {

    /**
     * 添加APi配置
     * @param record
     * @return
     */
    int insertSelective(API record);

    /**
     * 修改APi配置
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(API record);

    /**
     * 获取启用的API
     * @return
     */
    @Select("select * from API where state =1 limit 0,1 ")
    API selectGetUseFAceApi();

    /**
     * 分页查询APi配置
     * @param counselorId
     * @param page
     * @param limit
     * @return
     */
    @Select("select api.*,(select counselor_name from counselor c where c.counselor_id = api.COUNSRLOR_ID) counselor_name " +
            "from API api where api.COUNSRLOR_ID = #{counselorId} limit #{page},#{limit}")
    List<API>  getApiListByPage(int counselorId ,int page ,int limit);

    /**
     * 获取API数量
     * @return
     */
    @Select("SELECT COUNT(1) FROM API")
    int getApiCount();
}