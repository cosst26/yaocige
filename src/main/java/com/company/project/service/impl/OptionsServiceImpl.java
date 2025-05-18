package com.company.project.service.impl;

import com.company.project.dao.OptionsMapper;
import com.company.project.model.Options;
import com.company.project.service.OptionsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class OptionsServiceImpl extends AbstractService<Options> implements OptionsService {

    @Resource
    private OptionsMapper tOptionsMapper;

}
