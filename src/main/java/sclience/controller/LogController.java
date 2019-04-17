package sclience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sclience.service.LogService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("log")
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping("findAllLogs")
    public Map<String,Object> findAllLogs(Integer page,Integer rows){
        HashMap<String, Object> result = new HashMap<>();
        //获取日志总记录数
        Integer totals = logService.findTotals();
        result.put("records", totals);
        //计算总页数
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        result.put("total", totalPage);
        result.put("page", page);
        result.put("rows", logService.findAllLogs(page, rows));
        return result;
    }
}
