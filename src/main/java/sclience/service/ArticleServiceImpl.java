package sclience.service;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sclience.conf.TestInterface;
import sclience.dao.ArticleMapper;
import sclience.entity.Article;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> findAllArticles(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开启分页
        return articleMapper.findAllArticles();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer findTotals() {
        Article article = new Article();
        return articleMapper.selectCount(article);
    }

    @Override
    @TestInterface("文章上架")
    public void addArticle(Article article) {
        //生成id
        article.setId(UUID.randomUUID().toString());
        //设置上架时间
        article.setPublishDate(new Date());
        //设置阅读起始条数为 0
        article.setReadNum(0);
        articleMapper.insertSelective(article);
        
    }

    @Override
    @TestInterface("更新文章")
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    @TestInterface("删除文章")
    public void delArticle(Article article) {
        articleMapper.deleteByPrimaryKey(article.getId());
    }
}
