package sclience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sclience.entity.Turn;
import sclience.service.TurnService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("turn")
public class TurnController {
    @Resource
    private TurnService turnService;
    @RequestMapping("findTurnsByPage")
    public Map<String,Object> findTurnsByPage(Integer page, Integer rows){
        HashMap<String, Object> result = new HashMap<>();
        //获取总条数
        Integer totals = turnService.findTotals();
        result.put("records", totals);
        //存入当前页
        result.put("page", page);
        //计算总页数
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        result.put("total", totalPage);
        result.put("rows", turnService.findAllTurnsByPage(page, rows));
        return result;
    }
    @RequestMapping("edit")
    public void edit(MultipartFile file,HttpServletRequest request,Turn turn,String oper) throws IOException {
        if (oper.equals("edit")){
            turnService.updateTurn(turn);
        }else if (oper.equals("add")){
            String realPath = request.getRealPath("/upload");
            turn.setTitle(file.getOriginalFilename());
            file.transferTo(new File(realPath,file.getOriginalFilename()));//文件上传
            //设置超链接
            System.out.println("超链接："+realPath+"\\"+file.getOriginalFilename());
            turn.setHyperlink(realPath+"\\"+file.getOriginalFilename());
            turn.setPicPath(realPath);
            turnService.addTurn(turn);
        }else if(oper.equals("del")){
            turnService.deleteTurn(turn);
        }

    }
    @RequestMapping("uploadOrUpdate")
    public void uploadOrUpdate(MultipartFile file, HttpServletRequest request, Turn turn) throws IOException {
        try {/*StringUtils.isEmpty(turn.getId())*/
            if (turn.getId()!=null && !"".equals(turn.getId())){
                turnService.updateTurn(turn);
            }else {
                System.out.println("接收到的参数："+file+"----------"+turn);
                String realPath = request.getRealPath("/upload");
                turn.setTitle(file.getOriginalFilename());
                file.transferTo(new File(realPath,file.getOriginalFilename()));//文件上传
                //设置超链接
                System.out.println("超链接："+realPath+"\\"+file.getOriginalFilename());
                turn.setHyperlink(realPath+"\\"+file.getOriginalFilename());
                turn.setPicPath(realPath);
                turnService.addTurn(turn);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("findTurnById")
    public Turn findTurnById(String id){
        Turn turn = new Turn();
        turn.setId(id);
        System.out.println("------------------"+id);
        Turn aa = turnService.findById(turn);
        System.out.println("------------------"+aa);
        return aa;

    }
    @RequestMapping("deleteTurn")
    public void deleteTurn(Turn turn){
        System.out.println("==================="+turn);
        turnService.deleteTurn(turn);
    }


}
