package com.helo.demo.service;


import com.helo.demo.mapper.LeaveNoticeMapper;
import com.helo.demo.model.LeaveNotice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (LeaveNotice)表服务实现类
 *
 * @author makejava
 * @since 2020-03-28 17:15:39
 */
@Service("leaveNoticeService")
public class LeaveNoticeService {
    @Resource
    private LeaveNoticeMapper leaveNoticeDao;

    /**
     * 新增数据
     *
     * @param leaveNotice 实例对象
     * @return 实例对象
     */

    public LeaveNotice insert(LeaveNotice leaveNotice) {
        this.leaveNoticeDao.insert(leaveNotice);
        return leaveNotice;
    }

    /**
    * @Description: 根据学生的ID查询请假未读的通知
    * @params: [stuId]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/3/28 6:31 PM
    */ 
    public int getNoRead(int stuId){
        return this.leaveNoticeDao.getNoRead(stuId);
    }

    /**
    * @Description: 根据学生ID查询请假通知
    * @params: [stuId]
    * @return: java.util.List<com.helo.demo.model.LeaveNotice>
    * @Author: wangxianlin
    * @Date: 2020/3/28 6:58 PM
    */ 
    public List<LeaveNotice> getLeaveNoticeByStuId(int stuId){
        return this.leaveNoticeDao.getLeaveNoticeByStuId(stuId);
    }

    /**
    * @Description: 已读
    * @params: [id]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/3/28 9:30 PM
    */
    public  int updateNoticeByid(int id){
        return this.leaveNoticeDao.updateNoticeByid(id);
    }
    
    /**
    * @Description: 教师已读
    * @params: [id]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:13 PM
    */ 
    public int updateNoticeByTeaId(int id){
        return this.leaveNoticeDao.updateNoticeByTeaId(id);
    }
    
    /**
    * @Description: 根据教师的ID查询未读的通知
    * @params: [teaId]
    * @return: java.util.List<com.helo.demo.model.LeaveNotice>
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:14 PM
    */ 
    public List<LeaveNotice> getLeaveNoticeByTeaId(int teaId){
        return this.leaveNoticeDao.getLeaveNoticeByTeaId(teaId);
    }
    
    /**
    * @Description: 统计教师未读的通知数量
    * @params: [teaId]
    * @return: int
    * @Author: wangxianlin
    * @Date: 2020/4/5 6:14 PM
    */ 
    public int getNoReadByTeaId(int teaId){
        return this.leaveNoticeDao.getNoReadByTeaId(teaId);
    }
}