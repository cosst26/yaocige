package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Zodiac;
import com.company.project.service.ZodiacService;
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
@RequestMapping("/zodiac")
@Api(tags = {"/zodiac"}, description = "生肖结果管理模块")
public class ZodiacController extends BaseController{
    @Resource
    private ZodiacService zodiacService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Zodiac zodiac) {
        zodiac.setCreatedAt(new Date());
        zodiac.setIsDelete(false);
        zodiac.setCreatedBy(super.getUserId());
        zodiacService.save(zodiac);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(zodiac);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Zodiac zodiac=new Zodiac();
        zodiac.setId(id);
        zodiac.setIsDelete(true);
        zodiacService.update(zodiac);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Zodiac zodiac) {
        zodiac.setUpdatedAt(new Date());
        zodiacService.update(zodiac);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(zodiac);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Zodiac());
        }else {
            Zodiac zodiac = zodiacService.findById(id);
            if (null == zodiac){
                return ResultGenerator.genSuccessResult(new Zodiac());
            }else {
                return ResultGenerator.genSuccessResult(zodiac);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Zodiac zodiac) {

        if (null == zodiac){
            zodiac = new Zodiac();
        }

        PageHelper.startPage(zodiac.getPage() == null ? 0 : zodiac.getPage(), zodiac.getLimit() == null ? 10 : zodiac.getLimit());
        zodiac.setIsDelete(false);
        List<Zodiac> list = zodiacService.findByModel(zodiac);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
