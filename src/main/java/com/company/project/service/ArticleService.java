package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Article;
import com.company.project.core.Service;

public interface ArticleService extends Service<Article> {

    Result list(Article article);

    Result detail(Long id);
}
