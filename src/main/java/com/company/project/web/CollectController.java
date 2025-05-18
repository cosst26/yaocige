package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Collect;
import com.company.project.service.CollectService;
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
@RequestMapping("/collect")
@Api(tags = {"/collect"}, description = "收藏管理模块")
public class CollectController extends BaseController{
    @Resource
    private CollectService collectService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Collect collect) {
        collect.setCreatedAt(new Date());
        collect.setIsDelete(false);
        collect.setCreatedBy(super.getUserId());
        collectService.save(collect);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(collect);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Collect collect=new Collect();
        collect.setId(id);
        collect.setIsDelete(true);
        collectService.update(collect);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Collect collect) {
        collect.setUpdatedAt(new Date());
        collectService.update(collect);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(collect);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Collect());
        }else {
            Collect collect = collectService.findById(id);
            if (null == collect){
                return ResultGenerator.genSuccessResult(new Collect());
            }else {
                return ResultGenerator.genSuccessResult(collect);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Collect collect) {

        if (null == collect){
            collect = new Collect();
        }

        PageHelper.startPage(collect.getPage() == null ? 0 : collect.getPage(), collect.getLimit() == null ? 10 : collect.getLimit());
        collect.setIsDelete(false);
        List<Collect> list = collectService.findByModel(collect);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
