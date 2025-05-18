package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Options;
import com.company.project.service.OptionsService;
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
@RequestMapping("/options")
@Api(tags = {"/options"}, description = "MBTI选项管理模块")
public class OptionsController extends BaseController{
    @Resource
    private OptionsService optionsService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Options options) {
        options.setCreatedAt(new Date());
        options.setIsDelete(false);
        options.setCreatedBy(super.getUserId());
        optionsService.save(options);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(options);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Options options=new Options();
        options.setId(id);
        options.setIsDelete(true);
        optionsService.update(options);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Options options) {
        options.setUpdatedAt(new Date());
        optionsService.update(options);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(options);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Options());
        }else {
            Options options = optionsService.findById(id);
            if (null == options){
                return ResultGenerator.genSuccessResult(new Options());
            }else {
                return ResultGenerator.genSuccessResult(options);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Options options) {

        if (null == options){
            options = new Options();
        }

        PageHelper.startPage(options.getPage() == null ? 0 : options.getPage(), options.getLimit() == null ? 10 : options.getLimit());
        options.setIsDelete(false);
        List<Options> list = optionsService.findByModel(options);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
