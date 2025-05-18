package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.MomentLike;
import com.company.project.service.MomentLikeService;
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
@RequestMapping("/moment/like")
@Api(tags = {"/moment/like"}, description = "点赞管理模块")
public class MomentLikeController extends BaseController{
    @Resource
    private MomentLikeService momentLikeService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody MomentLike momentLike) {
        momentLike.setCreatedAt(new Date());
        momentLike.setIsDelete(false);
        momentLike.setCreatedBy(super.getUserId());
        momentLikeService.save(momentLike);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(momentLike);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        MomentLike momentLike=new MomentLike();
        momentLike.setId(id);
        momentLike.setIsDelete(true);
        momentLikeService.update(momentLike);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody MomentLike momentLike) {
        momentLike.setUpdatedAt(new Date());
        momentLikeService.update(momentLike);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(momentLike);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new MomentLike());
        }else {
            MomentLike momentLike = momentLikeService.findById(id);
            if (null == momentLike){
                return ResultGenerator.genSuccessResult(new MomentLike());
            }else {
                return ResultGenerator.genSuccessResult(momentLike);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) MomentLike momentLike) {

        if (null == momentLike){
            momentLike = new MomentLike();
        }

        PageHelper.startPage(momentLike.getPage() == null ? 0 : momentLike.getPage(), momentLike.getLimit() == null ? 10 : momentLike.getLimit());
        momentLike.setIsDelete(false);
        List<MomentLike> list = momentLikeService.findByModel(momentLike);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
