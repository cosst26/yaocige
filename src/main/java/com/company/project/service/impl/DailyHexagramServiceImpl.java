package com.company.project.service.impl;

import com.company.project.dao.DailyHexagramMapper;
import com.company.project.model.DailyHexagram;
import com.company.project.service.DailyHexagramService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DailyHexagramServiceImpl extends AbstractService<DailyHexagram> implements DailyHexagramService {

    @Resource
    private DailyHexagramMapper tDailyHexagramMapper;

}
