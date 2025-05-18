package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.DialogueDetails;
import com.company.project.service.DialogueDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/dialogue/details")
@Api(tags = {"/dialogue/details"}, description = "AI对话详情管理模块")
public class DialogueDetailsController extends BaseController {
    @Resource
    private DialogueDetailsService dialogueDetailsService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody DialogueDetails dialogueDetails) {
        dialogueDetails.setCreatedAt(new Date());
        dialogueDetails.setIsDelete(false);
        dialogueDetails.setCreatedBy(super.getUserId());
        dialogueDetailsService.save(dialogueDetails);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(dialogueDetails);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        DialogueDetails dialogueDetails=new DialogueDetails();
        dialogueDetails.setId(id);
        dialogueDetails.setIsDelete(true);
        dialogueDetailsService.update(dialogueDetails);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody DialogueDetails dialogueDetails) {
        dialogueDetails.setUpdatedAt(new Date());
        dialogueDetailsService.update(dialogueDetails);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(dialogueDetails);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new DialogueDetails());
        }else {
            DialogueDetails dialogueDetails = dialogueDetailsService.findById(id);
            if (null == dialogueDetails){
                return ResultGenerator.genSuccessResult(new DialogueDetails());
            }else {
                return ResultGenerator.genSuccessResult(dialogueDetails);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) DialogueDetails dialogueDetails) {
        dialogueDetails.setSenderId(Long.valueOf(super.getUserId()));
        return dialogueDetailsService.list(dialogueDetails);
    }
}
