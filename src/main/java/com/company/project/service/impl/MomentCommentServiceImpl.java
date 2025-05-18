package com.company.project.service.impl;

import com.company.project.dao.MomentCommentMapper;
import com.company.project.model.MomentComment;
import com.company.project.service.MomentCommentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MomentCommentServiceImpl extends AbstractService<MomentComment> implements MomentCommentService {

    @Resource
    private MomentCommentMapper momentCommentMapper;

    @Override
    public List<MomentComment> selectByMomentId(Long momentId) {
        return momentCommentMapper.selectByMomentId(momentId);
    }
}
