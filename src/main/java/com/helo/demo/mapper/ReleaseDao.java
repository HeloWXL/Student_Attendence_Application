package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Release;
import com.helo.demo.vo.ReleaseCourseProfessionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * (Release)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-05 23:35:40
 */
@Mapper
public interface ReleaseDao extends BaseMapper<Release> {

    /**
     * 通过ID查询单条数据
     *
     * @param releaseId 主键
     * @return 实例对象
     */
    Release queryById(Integer releaseId);

    /**
     * 修改数据
     *
     * @param release 实例对象
     * @return 影响行数
     */
    int update(Release release);

    /**
    * @Description: 根据教师ID查询已发布的考勤
    * @params: [tid]
    * @return: java.util.List<com.helo.demo.vo.ReleaseCourseProfessionVo>
    * @Author: wangxianlin
    * @Date: 2020/3/6 9:54 AM
    */ 
    @Select("select  r.start_time ,r.end_time ,p.profession_name,c.course_name from appdemo.release r,profession p ,course c where " +
            "r.teacher_id = #{tid} AND r.profession_id =p.profession_id  and c.course_id = r.course_id order by r.start_time desc")
    List<ReleaseCourseProfessionVo> getReleaseByTid(int tid);
}