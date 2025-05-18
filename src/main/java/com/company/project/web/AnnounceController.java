package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Announce;
import com.company.project.service.AnnounceService;
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
@RequestMapping("/announce")
@Api(tags = {"/announce"}, description = "公告管理模块")
public class AnnounceController extends BaseController{
    @Resource
    private AnnounceService announceService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Announce announce) {
        announce.setCreatedAt(new Date());
        announce.setIsDelete(false);
        announce.setCreatedBy(super.getUserId());
        announceService.save(announce);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(announce);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Announce announce=new Announce();
        announce.setId(id);
        announce.setIsDelete(true);
        announceService.update(announce);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Announce announce) {
        announce.setUpdatedAt(new Date());
        announceService.update(announce);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(announce);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Announce());
        }else {
            Announce announce = announceService.findById(id);
            if (null == announce){
                return ResultGenerator.genSuccessResult(new Announce());
            }else {
                return ResultGenerator.genSuccessResult(announce);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Announce announce) {

        if (null == announce){
            announce = new Announce();
        }

        PageHelper.startPage(announce.getPage() == null ? 0 : announce.getPage(), announce.getLimit() == null ? 10 : announce.getLimit());
        announce.setIsDelete(false);
        List<Announce> list = announceService.findByModel(announce);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
