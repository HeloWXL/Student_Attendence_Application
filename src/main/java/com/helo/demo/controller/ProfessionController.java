package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Profession;
import com.helo.demo.service.ProfessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xiayj
 * @ClassName ProfessionController
 * @Description
 * @date 2019/8/21 0:03
 */
@Api(tags = "专业接口")
@RestController
@RequestMapping("profession")
public class ProfessionController {

  @Resource
  private ProfessionService professionService;

  @ApiOperation(value = "根据ID查询专业信息")
  @GetMapping("/selectByProfessionId/{id}")
  public DataResult<Profession> selectByProfessionId(@RequestParam("id") Integer id){
    DataResult<Profession> result = new DataResult<>();
    result.setBody(professionService.selectByPrimaryKey(id));
    return result;
  }

  @ApiOperation(value = "根据ID删除专业信息")
  @GetMapping("/deleteByStudentId/{id}")
  public DataResult<Integer> deleteByStudentId(@RequestParam("id") Integer id){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(professionService.deleteByPrimaryKey(id));
    return result;
  }

  @ApiOperation(value = "添加专业信息")
  @PostMapping("/insertProfession")
  public DataResult<Integer> insertProfession(@RequestBody Profession profession){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(professionService.insertSelective(profession));
    return result;
  }

  @ApiOperation(value = "修改专业信息")
  @PostMapping("/updateProfession")
  public DataResult<Integer> updateProfession(@RequestBody Profession profession){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(professionService.updateByPrimaryKeySelective(profession));
    return result;
  }

  @ApiOperation(value = "查询专业信息-分页显示")
  @GetMapping("/selectProfessionByPage")
  public DataResult<Map<String,Object>> selectProfessionByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSieze") Integer pageSieze){
    DataResult<Map<String,Object>> result = new DataResult<>();
    result.setBody(professionService.getProfessionByPage(pageNo,pageSieze));
    return result;
  }
}
