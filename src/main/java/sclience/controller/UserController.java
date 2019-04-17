package sclience.controller;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sclience.entity.User;
import sclience.entity.UserVO;
import sclience.service.UserService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
    @RequestMapping("export")//导出数据
    public void export(){
        HSSFWorkbook sheets = new HSSFWorkbook();
        // 创建表 参数：表名
        HSSFSheet sheet = sheets.createSheet("user");
        //获取行 参数：下标
        HSSFRow row = sheet.createRow(0);
        //设置表头字体格式
        HSSFCellStyle fontStyle = sheets.createCellStyle();
        HSSFFont font = sheets.createFont();
        font.setBold(true);
        font.setColor((short) 12);
        font.setFontName("黑体");
        fontStyle.setFont(font);
        String[] strings = {"id","userName","password","phone","sex","nickName","name","province","city","autograph","headPic","status","registTime","lastLoginTime"};
        for (int i = 0; i < strings.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(strings[i]);
            cell.setCellStyle(fontStyle);
        }
        //设置时间格式
        HSSFCellStyle dateStyle = sheets.createCellStyle();
        HSSFDataFormat dataFormat = sheets.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        dateStyle.setDataFormat(format);
        List<User> users = userService.findAllUsers();
        for (int i = 0; i < users.size(); i++) {
            HSSFRow listRow = sheet.createRow(i + 1);
            Integer a = 0;
            listRow.createCell(a++).setCellValue(users.get(i).getId());
            listRow.createCell(a++).setCellValue(users.get(i).getUserName());
            listRow.createCell(a++).setCellValue(users.get(i).getPassword());
            listRow.createCell(a++).setCellValue(users.get(i).getPhone());
            listRow.createCell(a++).setCellValue(users.get(i).getSex());
            listRow.createCell(a++).setCellValue(users.get(i).getNickName());
            listRow.createCell(a++).setCellValue(users.get(i).getName());
            listRow.createCell(a++).setCellValue(users.get(i).getProvince());
            listRow.createCell(a++).setCellValue(users.get(i).getCity());
            listRow.createCell(a++).setCellValue(users.get(i).getAutograph());
            listRow.createCell(a++).setCellValue(users.get(i).getHeadPic());
            listRow.createCell(a++).setCellValue(users.get(i).getStatus());
            HSSFCell registTime = listRow.createCell(a++);
            registTime.setCellValue(users.get(i).getRegistTime());
            registTime.setCellStyle(dateStyle);
            HSSFCell lastLoginTime = listRow.createCell(a++);
            lastLoginTime.setCellValue(users.get(i).getLastloginTime());
            lastLoginTime.setCellStyle(dateStyle);
        }
        try {
            sheets.write(new File("D:/SclienceData/framework/后期项目/jingjing/userSheet.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("findProvinces")
    public Map<String,List<UserVO>> findProvinces(){
        List<UserVO> vos = userService.findCountGroupByProvince();
        HashMap<String, List<UserVO>> map = new HashMap<>();
        map.put("data", vos);
        return map;
    }

}
