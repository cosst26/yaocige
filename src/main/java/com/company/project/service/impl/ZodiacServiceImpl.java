package com.company.project.service.impl;

import com.company.project.dao.ZodiacMapper;
import com.company.project.model.Zodiac;
import com.company.project.service.ZodiacService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ZodiacServiceImpl extends AbstractService<Zodiac> implements ZodiacService {

    @Resource
    private ZodiacMapper tZodiacMapper;

}
