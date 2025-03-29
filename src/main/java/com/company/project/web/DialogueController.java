package com.company.project.web;

import com.company.project.annotation.VerifyIdempotent;
import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Dialogue;
import com.company.project.service.DialogueService;
import com.company.project.vo.ChatVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/dialogue")
@Api(tags = {"/dialogue"}, description = "AI对话列表管理模块")
public class DialogueController extends BaseController {
    @Resource
    private DialogueService dialogueService;

    @ApiOperation(value = "人工智能对话", notes = "人工智能对话")
    @RequestMapping(value = "/chineseGPT", method = {RequestMethod.POST})
    @VerifyIdempotent(value = "/dialogue/chineseGPT", expireMillis = 10L)
    public Result chineseGPT(@RequestBody ChatVo chatVo) {
        return dialogueService.chineseGPT(super.getUserId(),chatVo);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Dialogue dialogue) {
        dialogue.setCreatedAt(new Date());
        dialogue.setIsDelete(false);
        dialogue.setCreatedBy(super.getUserId());
        dialogueService.save(dialogue);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(dialogue);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Dialogue dialogue=new Dialogue();
        dialogue.setId(id);
        dialogue.setIsDelete(true);
        dialogueService.update(dialogue);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Dialogue dialogue) {
        dialogue.setUpdatedAt(new Date());
        dialogueService.update(dialogue);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(dialogue);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Dialogue());
        }else {
            Dialogue dialogue = dialogueService.findById(id);
            if (null == dialogue){
                return ResultGenerator.genSuccessResult(new Dialogue());
            }else {
                return ResultGenerator.genSuccessResult(dialogue);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Dialogue dialogue) {
        return dialogueService.list(super.getUserId(),dialogue);
    }
}
