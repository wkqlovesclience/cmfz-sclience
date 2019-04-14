package sclience.controller;

import it.sauronsoftware.jave.Encoder;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sclience.entity.Album;
import sclience.entity.Chapter;
import sclience.service.AlbumService;
import sclience.service.ChapterService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Resource
    private AlbumService albumService;
    @Resource
    private ChapterService chapterService;
    @RequestMapping("findAllChaptersByPage")
    public Map<String,Object> findAllChaptersByPage(Integer page,Integer rows){
        Map<String, Object> result = new HashMap<>();
        //获取章节总记录数
        Integer totals = chapterService.findTotals();
        result.put("records", totals);
        //计算总页数
        Integer totalPage = totals%rows==0?totals/rows:totals/rows+1;
        result.put("total", totalPage);
        result.put("page", page);
        result.put("rows", chapterService.findAllChaptersByPage(page, rows));
        return result;
    }
    @RequestMapping("delChapter")
    public void delChapter(Chapter chapter){
        delChapter(chapter);
    }
    @RequestMapping("download")//下载
    public void download(HttpServletResponse response, HttpServletRequest request,String openStyle,String path) throws IOException {
        //根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        //根据获取的文件名获取指定目录下的文件
        File file = new File(realPath, path);
        String fileName = file.getName();
        FileInputStream is = new FileInputStream(file);
        response.setHeader("content-disposition", openStyle+";fileName"+ URLEncoder.encode(fileName, "UTF-8"));
        //动态获取文件类型
        String type = request.getSession().getServletContext().getMimeType(fileName.substring(fileName.lastIndexOf(".")));
        //String extension = FilenameUtils.getExtension(name);
        //String contextPath = request.getSession().getServletContext().getContextPath();
        response.setContentType(type);
        //获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);



    }
    @RequestMapping("upload")//上传
    public Map<String,Object> upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response,Chapter chapter) throws UnsupportedEncodingException {
        System.out.println("file=======================:"+file);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //创建Map，用于返回
        HashMap<String, Object> map = new HashMap<>();
        //设置初始返回Map为成功，如果以下代码有异常，则对Map进行覆盖
        map.put("status", true);
        map.put("message", "添加成功！");
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File file1 = new File(realPath, file.getOriginalFilename());
        //文件上传
        try {
            file.transferTo(file1);
        } catch (IOException e) {//上传失败，定义返回的Map失败
            e.printStackTrace();
            map.put("status", false);
            map.put("message", "添加失败！");
        }
        //计算音频时长和大小
        long length = 0L;
        Encoder encoder = new Encoder();
        /*Chapter chapter = new Chapter();*/
        try {
            //getInfo()的参数是一个File,不能用MultipartFile
            //getDuration()获取得到的文件时长是一个以毫秒为单位的long类型的数值
            length = encoder.getInfo(new File(realPath, file.getOriginalFilename())).getDuration();
            chapter.setAudioTime(length/1000/60+"分"+length/1000%60+"秒");
            System.out.println("-----------时长：------------"+length);
        }catch (Exception e) {
            //如果这里有异常也定义返回的map为失败
            e.printStackTrace();
            map.put("status", false);
            map.put("message", "添加音乐失败");
        }

        System.out.println("时长："+length/1000/60+"分"+length/1000%60+"秒");
        //得到音频内存大小，是一个以字节为单位的long类型的数值
        chapter.setAudioSize(file.getSize());
        System.out.println("getOriginalFilename():"+file.getOriginalFilename()+""+file.getName());
        chapter.setName(file.getOriginalFilename());
        chapter.setAudioPath("upload\\"+file.getOriginalFilename());
        chapter.setAlbumId(chapter.getAlbum().getId());
        chapterService.uploadChapter(chapter);
        System.out.println("chapter:-----------"+chapter);
        List<Album> albums = albumService.findAllAlbums();
        map.put("albums", albums);
        return map;
    }
    @RequestMapping("findAllAlbums")
    public List<Album> findAllAlbums(){
        return albumService.findAllAlbums();
    }

    /*@RequestMapping("uploadMusic")
    @ResponseBody
    public Map<String,Object> uploadMusic(HttpServletRequest request,MultipartFile music){
        //创建一个map，用于返回
        Map<String, Object> map = new HashMap<String,Object>();
        //设置初始返回map为成功，如果一下代码有异常则对map进行覆盖
        map.put("status", true);
        map.put("message", "添加音乐成功");
        //根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/cmfz/show/album/music");
        //创建一个file，用于文件上传，其实也是为了在下边获取文件大小的时候用（获取文件大小的参数类型是File，不是MultipartFile）
        File file = new File(realPath,music.getOriginalFilename());
        //文件上传
        try {
            music.transferTo(file);
        } catch (Exception e) {
            //如果文件上传失败，定义返回的map失败
            map.put("status", false);
            map.put("message", "添加音乐失败");
        }
        //计算音频时长大小
        long length = 0l;
        Encoder encoder = new Encoder();
        try {
            //getInfo()的参数是一个File,不能用MultipartFile
            //getDuration()获取得到的文件时长是一个以毫秒为单位的long类型的数值
            length = encoder.getInfo(file).getDuration();
        } catch (Exception e) {
            //如果这里有异常也定义返回的map为失败
            map.put("status", false);
            map.put("message", "添加音乐失败");
        }
        //打印一下文件时长
        System.out.println(length/1000/60+"分"+length/1000%60+"秒");
        //得到音频内存大小，是一个以字节为单位的long类型的数值
        //在此我用BigDecimal将其保留两位小数
        BigDecimal size = new BigDecimal(music.getSize());
        BigDecimal mod = new BigDecimal(1024);
        //除两个1024，保留两位小数，进行四舍五入
        size = size.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP);

        //如果需要进行入库可在接下进行代码的书写

        return map;
    }
*/

}
