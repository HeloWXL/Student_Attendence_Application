package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.AdminMapper;
import com.helo.demo.model.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2020-04-19 10:16:37
 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
    * @Description: 管理员登录
    * @params: [userName]
    * @return: com.helo.demo.model.Admin
    * @Date: 2020/4/19 10:23 AM
    */ 
    public Admin checkLogin(String userName){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("name",userName);
        List<Admin> admins = adminMapper.selectList(entityWrapper);
        return admins.size()>0?admins.get(0):null;
    }
}