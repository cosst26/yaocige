package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Question;
import com.company.project.service.QuestionService;
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
@RequestMapping("/question")
@Api(tags = {"/question"}, description = "MBTI管理模块")
public class QuestionController extends BaseController{
    @Resource
    private QuestionService questionService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Question question) {
        question.setCreatedAt(new Date());
        question.setIsDelete(false);
        question.setCreatedBy(super.getUserId());
        questionService.save(question);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(question);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Question question=new Question();
        question.setId(id);
        question.setIsDelete(true);
        questionService.update(question);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Question question) {
        question.setUpdatedAt(new Date());
        questionService.update(question);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(question);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Question());
        }else {
            Question question = questionService.findById(id);
            if (null == question){
                return ResultGenerator.genSuccessResult(new Question());
            }else {
                return ResultGenerator.genSuccessResult(question);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Question question) {

        if (null == question){
            question = new Question();
        }

        PageHelper.startPage(question.getPage() == null ? 0 : question.getPage(), question.getLimit() == null ? 10 : question.getLimit());
        question.setIsDelete(false);
        List<Question> list = questionService.findByModel(question);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
