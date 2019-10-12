package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Leave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *
 * @author
 * @since
 */
@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {

    /**
     * 添加
     * @param record
     * @return
     */
    int insertSelective(Leave record);

    /**
     * 根据ID查询请假信息
     * @param leaveId
     * @return
     */
    Leave selectByPrimaryKey(Integer leaveId);



    /**
     * 根据学生的学号查询 -分页
     * @param map
     * @return
     */
    List<Leave> selectLeaveAndStudentByPage(Map<String, Object> map);


    /**
     * 请假列表-分页
     * @param map
     * @return
     */
    List<Leave> selectByPage(Map<String, Object> map);

}