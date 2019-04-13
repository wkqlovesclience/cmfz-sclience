package sclience.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sclience.entity.Admin;
import sclience.service.AdminService;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private DefaultKaptcha defaultKaptcha;
    @RequestMapping("getCode")
    public @ResponseBody void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        String text = defaultKaptcha.createText();
        System.out.println("图片验证码："+text);
        session.setAttribute("code", text);
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
    @RequestMapping("checkCode")
    public @ResponseBody boolean checkCode(String code,HttpSession session){
        String checkCode = (String) session.getAttribute("code");
        if (checkCode.equalsIgnoreCase(code)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("login")
    public @ResponseBody
    Map<String,Object> login(Admin admin, HttpSession session){
        System.out.println("用户输入的管理员信息："+admin);
        HashMap<String, Object> map = new HashMap<>();
        try {
            Admin login = adminService.login(admin);
            if (login!=null){
                map.put("success", true);
                session.setAttribute("loginAdmin", login);
            }
        }catch (Exception e){
           String message = e.getMessage();
           map.put("success", false);
           map.put("message", message);
        }
        return map;
    }
}
