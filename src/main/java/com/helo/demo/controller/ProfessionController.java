package com.helo.demo.controller;

import com.helo.demo.config.DataResult;
import com.helo.demo.model.Profession;
import com.helo.demo.service.ProfessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xiayj
 * @ClassName ProfessionController
 * @Description
 * @date 2019/8/21 0:03
 */
@Api(tags = "专业接口")
@Controller
@RequestMapping("professionApi")
public class ProfessionController {

  @Resource
  private ProfessionService professionService;

  @ApiOperation(value = "跳转到专业管理界面")
  @GetMapping("/toCounselorProfessionTable")
  public String toCounselorTable(){
    return "counselor/profession";
  }

  @ApiOperation(value = "根据ID查询专业信息")
  @GetMapping("/selectByProfessionId/{id}")
  @ResponseBody
  public DataResult<Profession> selectByProfessionId(@RequestParam("id") Integer id){
    DataResult<Profession> result = new DataResult<>();
    result.setBody(professionService.selectByPrimaryKey(id));
    return result;
  }

  @ApiOperation(value = "根据ID删除专业信息")
  @GetMapping("/deleteByStudentId")
  @ResponseBody
  public DataResult<Integer> deleteByStudentId(@RequestParam("professionId") Integer professionId){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(professionService.deleteByPrimaryKey(professionId));
    return result;
  }

  @ApiOperation(value = "添加专业信息")
  @PostMapping("/insertProfession")
  @ResponseBody
  public DataResult<Integer> insertProfession(@RequestBody Profession profession){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(professionService.insertSelective(profession));
    return result;
  }

  @ApiOperation(value = "修改专业信息")
  @PostMapping("/updateProfession")
  @ResponseBody
  public DataResult<Integer> updateProfession(@RequestBody Profession profession){
    DataResult<Integer> result = new DataResult<>();
    result.setBody(professionService.updateByPrimaryKeySelective(profession));
    return result;
  }

  @ApiOperation(value = "查询专业信息-分页显示")
  @GetMapping("/selectProfessionByPage")
  @ResponseBody
  public Map<String,Object> selectProfessionByPage(@RequestParam("page") Integer pageNo, @RequestParam("limit") Integer pageSieze){
    return professionService.getProfessionByPage(pageNo,pageSieze);
  }

  @ApiOperation(value = "加载专业信息")
  @GetMapping("/loadProfession")
  @ResponseBody
  public List<Profession> loadProfession(){
  return professionService.loadProfession();
  }

}
