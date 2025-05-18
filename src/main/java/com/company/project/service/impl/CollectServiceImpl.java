package com.company.project.service.impl;

import com.company.project.dao.CollectMapper;
import com.company.project.model.Collect;
import com.company.project.service.CollectService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CollectServiceImpl extends AbstractService<Collect> implements CollectService {

    @Resource
    private CollectMapper tCollectMapper;

}
