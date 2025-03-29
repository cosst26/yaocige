package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.DialogueDetailsMapper;
import com.company.project.model.DialogueDetails;
import com.company.project.service.DialogueDetailsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class DialogueDetailsServiceImpl extends AbstractService<DialogueDetails> implements DialogueDetailsService {

    @Resource
    private DialogueDetailsMapper dialogueDetailsMapper;

    @Override
    public Result list(DialogueDetails dialogueDetails) {

        if (null == dialogueDetails){
            dialogueDetails = new DialogueDetails();
        }

        PageHelper.startPage(dialogueDetails.getPage() == null ? 0 : dialogueDetails.getPage(), dialogueDetails.getLimit() == null ? 10 : dialogueDetails.getLimit());
        dialogueDetails.setIsDelete(false);
        List<DialogueDetails> list = dialogueDetailsMapper.list(dialogueDetails);
        if (list != null && list.size() > 0){
            for (DialogueDetails d:list) {
                if (dialogueDetails.getSenderId().equals(d.getSenderId())){
                    d.setType(1);
                }else {
                    d.setType(2);
                }
            }
        }
        list.sort(new Comparator<DialogueDetails>() {
            @Override
            public int compare(DialogueDetails o1, DialogueDetails o2) {
                Integer i1 = new BigDecimal(o1.getCreatedAt().getTime()).intValue();
                Integer i2 = new BigDecimal(o2.getCreatedAt().getTime()).intValue();
                //i2.compareTo(i1)倒叙
                //i1.compareTo(i2)正序
                return i1.compareTo(i2);
            }
        });
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
