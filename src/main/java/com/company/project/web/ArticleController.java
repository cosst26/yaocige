package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Article;
import com.company.project.service.ArticleService;
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
@RequestMapping("/article")
@Api(tags = {"/article"}, description = "文章管理模块")
public class ArticleController extends BaseController{
    @Resource
    private ArticleService articleService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Article article) {
        article.setCreatedAt(new Date());
        article.setIsDelete(false);
        article.setCreatedBy(super.getUserId());
        articleService.save(article);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(article);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Article article=new Article();
        article.setId(id);
        article.setIsDelete(true);
        articleService.update(article);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Article article) {
        article.setUpdatedAt(new Date());
        articleService.update(article);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(article);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        return articleService.detail(id);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Article article) {
        return articleService.list(article);
    }
}
