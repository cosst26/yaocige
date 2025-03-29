package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.DailyHexagram;
import com.company.project.service.DailyHexagramService;
import com.company.project.common.BaseController;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/daily/hexagram")
@Api(tags = {"/daily/hexagram"}, description = "每日一卦管理模块")
public class DailyHexagramController extends BaseController{
    @Resource
    private DailyHexagramService dailyHexagramService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody DailyHexagram dailyHexagram) {
        dailyHexagram.setCreatedAt(new Date());
        dailyHexagram.setIsDelete(false);
        dailyHexagram.setCreatedBy(super.getUserId());
        dailyHexagramService.save(dailyHexagram);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(dailyHexagram);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        DailyHexagram dailyHexagram=new DailyHexagram();
        dailyHexagram.setId(id);
        dailyHexagram.setIsDelete(true);
        dailyHexagramService.update(dailyHexagram);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody DailyHexagram dailyHexagram) {
        dailyHexagram.setUpdatedAt(new Date());
        dailyHexagramService.update(dailyHexagram);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(dailyHexagram);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new DailyHexagram());
        }else {
            DailyHexagram dailyHexagram = dailyHexagramService.findById(id);
            if (null == dailyHexagram){
                return ResultGenerator.genSuccessResult(new DailyHexagram());
            }else {
                return ResultGenerator.genSuccessResult(dailyHexagram);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) DailyHexagram dailyHexagram) {

        if (null == dailyHexagram){
            dailyHexagram = new DailyHexagram();
        }

        PageHelper.startPage(dailyHexagram.getPage() == null ? 0 : dailyHexagram.getPage(), dailyHexagram.getLimit() == null ? 10 : dailyHexagram.getLimit());
        dailyHexagram.setIsDelete(false);
        List<DailyHexagram> list = dailyHexagramService.findByModel(dailyHexagram);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
