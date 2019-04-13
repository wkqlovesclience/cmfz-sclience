package sclience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sclience.entity.Album;
import sclience.service.AlbumService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Resource
    private AlbumService albumService;
    @RequestMapping("findAllAlbumsByPage")
    public Map<String,Object> findAllAlbumsByPage(Integer page,Integer rows){
        Map<String, Object> result = new HashMap<>();
        //获取专辑总记录数
        Integer totals = albumService.findTotals();
        result.put("records", totals);
        //计算总页数
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        result.put("total", totalPage);
        //存入当前页
        result.put("page", page);
        //获取总记录 并存入
        result.put("rows", albumService.findAllAlbumsByPage(page, rows));
        return result;
    }
    @RequestMapping("edit")
    public void edit(MultipartFile file, HttpServletRequest request,String oper, Album album) throws IOException {
        if (oper.equals("edit")){
            albumService.updateAlbum(album);
        }else if (oper.equals("add")){
            addOrUpdate(file, request, album);
        }else if (oper.equals("del")){
            albumService.deleteAlbum(album);
        }
    }
    @RequestMapping("addOrUpdate")
    public void addOrUpdate(MultipartFile file, HttpServletRequest request,Album album) throws IOException {
        if (album.getId()!=null && !"".equals(album.getId())){
            albumService.updateAlbum(album);
        }else {
            System.out.println("========addOrUpdate========"+file);
            String realPath = request.getRealPath("/upload");
            System.out.println("realPath:"+realPath);
            file.transferTo(new File(realPath,file.getOriginalFilename()));
            album.setAlbumPic("upload\\"+file.getOriginalFilename());
            album.setCover(file.getOriginalFilename());
            System.out.println("---------addOrUpdate-------"+album);
            albumService.addAlbum(album);
        }
    }
    @RequestMapping("findAlbumById")
    public Album findAlbumById(Album album){
        System.out.println("findAlbumById:"+album);
        Album oneAlbum = albumService.findOneAlbum(album);
        System.out.println("oneAlbum:"+oneAlbum);
        return oneAlbum;
    }
    @RequestMapping("deleteAlbum")
    public void deleteAlbum(Album album){
        albumService.deleteAlbum(album);
    }

}
