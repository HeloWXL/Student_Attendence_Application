package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.LeaveMapper;
import com.helo.demo.model.Leave;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @ClassName LeaveService
 * @Description TODO
 * @date 2019/8/23 17:50
 * @Version 1.0
 */
@Service
public class LeaveService {

    @Resource
    private LeaveMapper leaveMapper;

    /**
     * 添加一条请假记录
     *
     * @param record
     * @return int
     */
    public int insertSelective(Leave record) {
        return leaveMapper.insertSelective(record);
    }


    /**
     * 根据ID查询出请假记录
     *
     * @param leaveId
     * @return
     */
    public Leave selectByPrimaryKey(Integer leaveId) {
        return leaveMapper.selectByPrimaryKey(leaveId);
    }


    /**
     * 获取请假列表分页 -根据学生的学号查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> getLeaveByPage(Integer pageNo, Integer pageSize, String studentSno) {
        EntityWrapper entityWrapper = new EntityWrapper();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        map.put("studentSno", studentSno);
        List<Leave> leaveList = leaveMapper.selectLeaveAndStudentByPage(map);
        data.put("list", leaveList);

        entityWrapper.eq("student_sno", studentSno);
        int count = leaveMapper.selectCount(entityWrapper);
        data.put("count", count);
        return data;
    }

    /**
     * 请假列表分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> selectByPage(Integer pageNo, Integer pageSize) {
        EntityWrapper entityWrapper = new EntityWrapper();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Leave> leaveList = leaveMapper.selectByPage(map);
        data.put("data", leaveList);

        int count = leaveMapper.selectCount(entityWrapper);
        data.put("count", count);
        data.put("msg", "");
        data.put("code", 0);
        return data;
    }


    /**
     * 同意请假
     *
     * @param leaveId
     * @return
     */
    public int agreeLeaves(int leaveId) {
        Leave leave = new Leave();
        leave.setLeaveId(leaveId);
        leave.setIsRead(1);
        return leaveMapper.updateById(leave);
    }

    /**
     * 不同意请假
     *
     * @param leaveId
     * @return
     */
    public int notAgreeLeaves(int leaveId) {
        Leave leave = new Leave();
        leave.setLeaveId(leaveId);
        leave.setIsRead(2);
        return leaveMapper.updateById(leave);
    }


    /**
    * @Description: 根据教师的ID查询学生的请假信息
    * @Author: wangxianlin
    * @Date: 2020/2/6 2:40 PM
    */ 
    public  List<Leave> getLeaveByTeacher(Integer tId){
        return leaveMapper.getLeaveByTeacher(tId);
    }

}
