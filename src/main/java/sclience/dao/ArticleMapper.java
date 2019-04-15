package sclience.dao;

import sclience.entity.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {
    /**
     * 获取所有文章 以及 文章所对应的上师
     */
    List<Article> findAllArticles();

}