package com.helo.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.helo.demo.mapper.ReleaseDao;
import com.helo.demo.model.Release;
import com.helo.demo.vo.ReleaseCourseProfessionVo;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
    * @Description: 根据教师ID查询发布的考勤
    * @params: [tid]
    * @return: java.util.List<com.helo.demo.vo.ReleaseCourseProfessionVo>
    * @Author: wangxianlin
    * @Date: 2020/3/6 9:40 AM
    */ 
    public  List<ReleaseCourseProfessionVo> getReleaseByTid(int tid){
        return this.releaseDao.getReleaseByTid(tid);
    }

    
    /**
    * @Description: 根据专业的ID查询最新的一条考勤
    * @params: [pid]
    * @return: com.helo.demo.model.Release
    * @Author: wangxianlin
    * @Date: 2020/3/6 10:51 AM
    */ 
    public Release getReleaseBypid(int pid){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("profession_id",pid);
        entityWrapper.orderBy("start_time",false);
        if(this.releaseDao.selectList(entityWrapper).size()>0){
            return (Release)this.releaseDao.selectList(entityWrapper).get(0);
        }else{
            return null;
        }

    }
}