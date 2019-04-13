package sclience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sclience.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("findAllUsersByPage")
    public Map<String,Object> findAllUsersByPage(Integer page,Integer rows){
        Map<String, Object> result = new HashMap<>();
        //获取用户信息总记录数
        Integer totals = userService.findTotals();
        result.put("records", totals);
        //计算总页数
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        result.put("total", totalPage);
        //存入当前页
        result.put("page", page);
        result.put("rows", userService.findAllUsersByPage(page, rows));
        return result;
    }

}
