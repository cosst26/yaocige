package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Message;
import com.company.project.service.MessageService;
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
@RequestMapping("/message")
@Api(tags = {"/message"}, description = "消息管理模块")
public class MessageController extends BaseController{
    @Resource
    private MessageService messageService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Message message) {
        message.setCreatedAt(new Date());
        message.setIsDelete(false);
        message.setCreatedBy(super.getUserId());
        messageService.save(message);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(message);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Message message=new Message();
        message.setId(id);
        message.setIsDelete(true);
        messageService.update(message);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Message message) {
        message.setUpdatedAt(new Date());
        messageService.update(message);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(message);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Message());
        }else {
            Message message = messageService.findById(id);
            if (null == message){
                return ResultGenerator.genSuccessResult(new Message());
            }else {
                return ResultGenerator.genSuccessResult(message);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Message message) {

        if (null == message){
            message = new Message();
        }

        PageHelper.startPage(message.getPage() == null ? 0 : message.getPage(), message.getLimit() == null ? 10 : message.getLimit());
        message.setIsDelete(false);
        List<Message> list = messageService.findByModel(message);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
