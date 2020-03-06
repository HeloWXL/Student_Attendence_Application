package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Release;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Release> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param release 实例对象
     * @return 对象列表
     */
    List<Release> queryAll(Release release);

    /**
     * 修改数据
     *
     * @param release 实例对象
     * @return 影响行数
     */
    int update(Release release);

    /**
     * 通过主键删除数据
     *
     * @param releaseId 主键
     * @return 影响行数
     */
    int deleteById(Integer releaseId);

    @Insert("INSERT INTO appdemo.release   ( teacher_id,  start_time,  end_time,  profession_id,  course_id )  VALUES " +
            "  ( #{teahcerId}, #{startTime},  #{endTime},  #{professionId},  #{courseId} )")
    int insertRelease(int teahcerId,Date startTime,Date endTime,Integer professionId,Integer courseId);

}