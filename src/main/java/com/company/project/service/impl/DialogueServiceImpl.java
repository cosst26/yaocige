package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.api.ChineseGPT;
import com.company.project.api.HttpRequest;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.DialogueMapper;
import com.company.project.model.Dialogue;
import com.company.project.model.DialogueDetails;
import com.company.project.model.User;
import com.company.project.service.DialogueDetailsService;
import com.company.project.service.DialogueService;
import com.company.project.service.UserService;
import com.company.project.vo.ChatVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DialogueServiceImpl extends AbstractService<Dialogue> implements DialogueService {

    @Resource
    private DialogueMapper dialogueMapper;

    @Resource
    private DialogueDetailsService dialogueDetailsService;

    @Resource
    private UserService userService;

    @Override
    public Result chineseGPT(String userId, ChatVo chatVo) {

        try {
            //1、获取token
            String access_token = new ChineseGPT().getWenxinToken();
            //2、访问数据
            String requestMethod = "POST";
            String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant?access_token="+access_token;//post请求时格式
            HashMap<String, String> msg = new HashMap<>();
            msg.put("role","user");
            msg.put("content", chatVo.getContent());
            ArrayList<HashMap> messages = new ArrayList<>();
            messages.add(msg);
            HashMap<String, Object> requestBody = new HashMap<>();
            requestBody.put("messages", messages);
            String outputStr = JSON.toJSONString(requestBody);
            JSON json = HttpRequest.httpRequest(url,requestMethod,outputStr,"application/json");

            //发送人信息
            User senderUser =  userService.findByIdAndValidDelete(Long.valueOf(userId));
            if (null == senderUser){
                return ResultGenerator.genFailResult("发送人信息不存在");
            }

            //接收人信息
            User recipientUser =  userService.findAdmin();
            if (null == recipientUser){
                return ResultGenerator.genFailResult("接收人信息不存在");
            }

            //记录发送信息
            DialogueDetails senderDialogueDetails = new DialogueDetails();
            senderDialogueDetails.setIsDelete(false);
            senderDialogueDetails.setCreatedAt(new Date());
            senderDialogueDetails.setCreatedBy(String.valueOf(senderUser.getId()));
            senderDialogueDetails.setSenderId(senderUser.getId());
            senderDialogueDetails.setSenderName(senderUser.getUserName());
            senderDialogueDetails.setSenderAvatar(senderUser.getAvatar());
            senderDialogueDetails.setDialogueId(chatVo.getDialogueId());
            senderDialogueDetails.setContent(chatVo.getContent());
            senderDialogueDetails.setRecipientId(recipientUser.getId());
            senderDialogueDetails.setRecipientName(recipientUser.getUserName());
            senderDialogueDetails.setRecipientAvatar(recipientUser.getAvatar());
            dialogueDetailsService.save(senderDialogueDetails);

            //记录回复信息
            DialogueDetails recipientDialogueDetails = new DialogueDetails();
            recipientDialogueDetails.setIsDelete(false);
            recipientDialogueDetails.setCreatedAt(new Date());
            recipientDialogueDetails.setCreatedBy(String.valueOf(recipientUser.getId()));

            recipientDialogueDetails.setSenderId(recipientUser.getId());
            recipientDialogueDetails.setSenderName(recipientUser.getUserName());
            recipientDialogueDetails.setSenderAvatar(recipientUser.getAvatar());

            recipientDialogueDetails.setRecipientId(senderUser.getId());
            recipientDialogueDetails.setRecipientName(senderUser.getUserName());
            recipientDialogueDetails.setRecipientAvatar(senderUser.getAvatar());

            recipientDialogueDetails.setDialogueId(chatVo.getDialogueId());
            recipientDialogueDetails.setContent(((JSONObject) json).getString("result"));
            dialogueDetailsService.save(recipientDialogueDetails);
            return ResultGenerator.genSuccessResult(json);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result list(String userId, Dialogue dialogue) {

        if (null == dialogue){
            dialogue = new Dialogue();
        }

        User user = userService.findByIdAndValidDelete(Long.valueOf(userId));
        if (null == user){
            return ResultGenerator.genFailResult("用户信息不存在[账号可能被停用或删除]");
        }

        if (1 == user.getRoleId()){
            dialogue.setCreatedBy(null);
        }else {
            dialogue.setCreatedBy(userId);
        }

        PageHelper.startPage(dialogue.getPage() == null ? 0 : dialogue.getPage(), dialogue.getLimit() == null ? 10 : dialogue.getLimit());
        dialogue.setIsDelete(false);
        List<Dialogue> list = dialogueMapper.list(dialogue);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
