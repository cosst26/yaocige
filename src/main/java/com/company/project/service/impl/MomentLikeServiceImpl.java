package com.company.project.service.impl;

import com.company.project.dao.MomentLikeMapper;
import com.company.project.model.MomentLike;
import com.company.project.service.MomentLikeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class MomentLikeServiceImpl extends AbstractService<MomentLike> implements MomentLikeService {

    @Resource
    private MomentLikeMapper tMomentLikeMapper;

}
