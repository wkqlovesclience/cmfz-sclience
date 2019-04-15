package sclience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sclience.service.ArticleService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @RequestMapping("findAllArticles")
    public Map<String,Object> findAllArticles(Integer page,Integer rows){
        HashMap<String, Object> result = new HashMap<>();
        //获取总记录数
        Integer totals = articleService.findTotals();
        result.put("records", totals);
        //计算总页数
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        result.put("total", totalPage);
        result.put("page", page);
        result.put("rows", articleService.findAllArticles(page,rows));
        return result;
    }
}
