package com.helo.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.helo.demo.model.Sign;
import com.helo.demo.vo.SignStudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SignMapper extends BaseMapper<Sign> {

    int insertSelective(Sign record);

    int updateByPrimaryKeySelective(Sign record);

    @Select("select * from sign s , student stu where  stu.student_id = s.student_id limit #{page},#{limit}")
    List<SignStudentVo> getSignList(@Param("page") Integer page , @Param("limit") Integer limit);

    @Select("SELECT * FROM sign where student_id =#{stuId} ORDER BY start_time desc limit 0,1")
    Sign getStudentSign(@Param("stuId") Integer stuId);

    @Update("update sign set end_time = now() , is_end_status = 1 where sign_id = #{signId}")
    int updateSignById(@Param("signId") int signId);
}