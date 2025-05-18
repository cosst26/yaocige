package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Article;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    List<Article> list(Article article);

    Article detail(Long id);
}