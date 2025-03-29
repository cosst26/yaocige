package com.company.project.service.impl;

import com.company.project.dao.MomentCommentMapper;
import com.company.project.model.MomentComment;
import com.company.project.service.MomentCommentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class MomentCommentServiceImpl extends AbstractService<MomentComment> implements MomentCommentService {

    @Resource
    private MomentCommentMapper tMomentCommentMapper;

}
