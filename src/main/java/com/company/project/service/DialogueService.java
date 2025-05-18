package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Dialogue;
import com.company.project.vo.ChatVo;

public interface DialogueService extends Service<Dialogue> {

    Result chineseGPT(String userId, ChatVo chatVo);

    Result list(String userId, Dialogue dialogue);
}
