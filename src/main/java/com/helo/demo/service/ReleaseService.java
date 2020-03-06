package com.helo.demo.service;

import com.helo.demo.mapper.ReleaseDao;
import com.helo.demo.model.Release;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Release)表服务接口
 *
 * @author makejava
 * @since 2020-03-05 23:35:41
 */
@Service
public class ReleaseService {

    @Resource
    private ReleaseDao releaseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param releaseId 主键
     * @return 实例对象
     */
    public Release queryById(Integer releaseId) {
        return this.releaseDao.queryById(releaseId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public List<Release> queryAllByLimit(int offset, int limit) {
        return this.releaseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param release 实例对象
     * @return 实例对象
     */
    public int insert(Release release) {
        return  this.releaseDao.insert(release);
    }

    /**
     * 修改数据
     *
     * @param release 实例对象
     * @return 实例对象
     */
    public Release update(Release release) {
        this.releaseDao.update(release);
        return this.queryById(release.getReleaseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param releaseId 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer releaseId) {
        return this.releaseDao.deleteById(releaseId) > 0;
    }

}