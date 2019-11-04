package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 教师Mapper
 * @author xiayj
 * @since
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    /**
     *根据教师ID删除教师信息
     * @param teacherId
     * @return
     */
    int deleteByPrimaryKey(Integer teacherId);

    /**
     * 添加教师信息
     * @param record
     * @return
     */
    int insertSelective(Teacher record);

    /**
     * 根据教师ID查询教师信息
     * @param tno
     * @return
     */
    Teacher selectByPrimaryKey(String tno);

    /**
     * 修改教师信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     * 根据教师工号查询教师信息
     * @param tno
     * @return
     */
    Teacher selectByTno(String tno);


    /**
     * 教师分页
     * @return List<Teacher>
     */
    List<Teacher> selectByPage(Map<String, Object> map);




}