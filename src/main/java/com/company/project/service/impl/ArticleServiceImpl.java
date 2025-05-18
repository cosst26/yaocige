package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.ArticleMapper;
import com.company.project.model.Article;
import com.company.project.service.ArticleService;
import com.company.project.core.AbstractService;
import com.company.project.service.MomentCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private MomentCommentService momentCommentService;

    @Override
    public Result list(Article article) {

        if (null == article){
            article = new Article();
        }

        PageHelper.startPage(article.getPage() == null ? 0 : article.getPage(), article.getLimit() == null ? 10 : article.getLimit());
        article.setIsDelete(false);
        List<Article> list = articleMapper.list(article);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result detail(Long id) {

        if (null == id){
            return ResultGenerator.genSuccessResult(new Article());
        }else {
            Article article = articleMapper.detail(id);
            if (null == article){
                return ResultGenerator.genSuccessResult(new Article());
            }else {

                article.setMomentCommentList(momentCommentService.selectByMomentId(id));
                return ResultGenerator.genSuccessResult(article);
            }
        }
    }
}
