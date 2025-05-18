package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.ArticleType;
import com.company.project.service.ArticleTypeService;
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
@RequestMapping("/article/type")
@Api(tags = {"/article/type"}, description = "文章分类管理模块")
public class ArticleTypeController extends BaseController{
    @Resource
    private ArticleTypeService articleTypeService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody ArticleType articleType) {
        articleType.setCreatedAt(new Date());
        articleType.setIsDelete(false);
        articleType.setCreatedBy(super.getUserId());
        articleTypeService.save(articleType);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(articleType);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        ArticleType articleType=new ArticleType();
        articleType.setId(id);
        articleType.setIsDelete(true);
        articleTypeService.update(articleType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody ArticleType articleType) {
        articleType.setUpdatedAt(new Date());
        articleTypeService.update(articleType);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(articleType);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new ArticleType());
        }else {
            ArticleType articleType = articleTypeService.findById(id);
            if (null == articleType){
                return ResultGenerator.genSuccessResult(new ArticleType());
            }else {
                return ResultGenerator.genSuccessResult(articleType);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) ArticleType articleType) {

        if (null == articleType){
            articleType = new ArticleType();
        }

        PageHelper.startPage(articleType.getPage() == null ? 0 : articleType.getPage(), articleType.getLimit() == null ? 10 : articleType.getLimit());
        articleType.setIsDelete(false);
        List<ArticleType> list = articleTypeService.findByModel(articleType);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
