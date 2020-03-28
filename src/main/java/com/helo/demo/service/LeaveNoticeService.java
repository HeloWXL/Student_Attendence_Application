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
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public LeaveNotice queryById(Integer id) {
        return this.leaveNoticeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */

    public List<LeaveNotice> queryAllByLimit(int offset, int limit) {
        return this.leaveNoticeDao.queryAllByLimit(offset, limit);
    }

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
     * 修改数据
     *
     * @param leaveNotice 实例对象
     * @return 实例对象
     */

    public LeaveNotice update(LeaveNotice leaveNotice) {
        this.leaveNoticeDao.update(leaveNotice);
        return this.queryById(leaveNotice.getId());
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.leaveNoticeDao.deleteById(id) > 0;
    }
}