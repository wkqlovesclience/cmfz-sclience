package sclience.service;

import sclience.entity.Article;

import java.util.List;

public interface ArticleService {
    /**
     * 获取所有文章以及文章所对应的作者
     */
    List<Article> findAllArticles(Integer pageNum,Integer pageSize);
    /**
     * 获取总记录
     */
    Integer findTotals();
    /**
     * 添加文章
     */
    void addArticle(Article article);
    /**
     * 更新文章
     */
    void updateArticle(Article article);
    /**
     * 删除文章
     */
    void delArticle(Article article);
}
