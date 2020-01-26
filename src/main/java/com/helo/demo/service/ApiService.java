package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.APIMapper;
import com.helo.demo.model.API;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ApiService
 * @Description TODO
 * @Date 2019/12/28 11:09 上午
 * @Created by wangxianlin
 */
@Service
public class ApiService {

    @Resource
    private APIMapper apiMapper;

    /**
     * 添加Api配置
     * @param api
     * @return
     */
    public int insertFaceApi(API api){
        return apiMapper.insertSelective(api);
    }

    /**
     * 修改Api配置
     * @param api
     * @return
     */
    public int updateFaceApi(API api){
        return apiMapper.updateByPrimaryKeySelective(api);
    }

    /**
     * 删除Api配置
     * @param ids
     * @return
     */
    public int deleteFaceApi(List<Integer> ids){
        return apiMapper.deleteBatchIds(ids);
    }

    /**
     * 获取启用的API
     * @return
     */
    public API selectGetUseFAceApi() throws Exception {
        if(apiMapper.selectGetUseFAceApi()==null){
            throw new Exception("获取API属性错误");
        }else{
            return apiMapper.selectGetUseFAceApi();
        }
    }

    public Map<String,Object> getApiListByPage(int counselorId , int page , int limit){
        Map<String,Object> map = new HashMap<>();
        List<API> apiList = apiMapper.getApiListByPage(counselorId,(page-1)*limit,limit);
        map.put("data",apiList);
        int count = apiMapper.getApiCount();
        map.put("count",count);
        map.put("msg","");
        map.put("code",0);
        return map;
    }


}
