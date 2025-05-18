package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.MomentComment;
import com.company.project.service.MomentCommentService;
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
@RequestMapping("/moment/comment")
@Api(tags = {"/moment/comment"}, description = "评论管理模块")
public class MomentCommentController extends BaseController{
    @Resource
    private MomentCommentService momentCommentService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody MomentComment momentComment) {
        momentComment.setCreatedAt(new Date());
        momentComment.setIsDelete(false);
        momentComment.setCreatedBy(super.getUserId());
        momentCommentService.save(momentComment);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(momentComment);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        MomentComment momentComment=new MomentComment();
        momentComment.setId(id);
        momentComment.setIsDelete(true);
        momentCommentService.update(momentComment);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody MomentComment momentComment) {
        momentComment.setUpdatedAt(new Date());
        momentCommentService.update(momentComment);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(momentComment);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new MomentComment());
        }else {
            MomentComment momentComment = momentCommentService.findById(id);
            if (null == momentComment){
                return ResultGenerator.genSuccessResult(new MomentComment());
            }else {
                return ResultGenerator.genSuccessResult(momentComment);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) MomentComment momentComment) {

        if (null == momentComment){
            momentComment = new MomentComment();
        }

        PageHelper.startPage(momentComment.getPage() == null ? 0 : momentComment.getPage(), momentComment.getLimit() == null ? 10 : momentComment.getLimit());
        momentComment.setIsDelete(false);
        List<MomentComment> list = momentCommentService.findByModel(momentComment);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
