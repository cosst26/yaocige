package com.company.project.service.impl;

import com.company.project.dao.AnnounceMapper;
import com.company.project.model.Announce;
import com.company.project.service.AnnounceService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AnnounceServiceImpl extends AbstractService<Announce> implements AnnounceService {

    @Resource
    private AnnounceMapper tAnnounceMapper;

}
