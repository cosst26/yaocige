package com.company.project.service.impl;

import com.company.project.dao.ArticleTypeMapper;
import com.company.project.model.ArticleType;
import com.company.project.service.ArticleTypeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ArticleTypeServiceImpl extends AbstractService<ArticleType> implements ArticleTypeService {

    @Resource
    private ArticleTypeMapper tArticleTypeMapper;

}
