package sclience;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sclience.dao.AdminMapper;
import sclience.dao.AlbumMapper;
import sclience.dao.ChapterMapper;
import sclience.entity.Admin;
import sclience.entity.Album;
import sclience.entity.Chapter;
import sclience.entity.Turn;
import sclience.service.AdminService;
import sclience.service.AlbumService;
import sclience.service.ChapterService;
import sclience.service.TurnService;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzSclienceApplicationTests {
    @Resource
    private ChapterService chapterService;
    @Resource
    private AlbumMapper albumMapper;
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private AlbumService albumService;
    @Resource
    private TurnService turnService;
    @Resource
    private AdminService adminService;
    @Resource
    private AdminMapper adminMapper;
    @Test
    public void contextLoads() {
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID().toString());
        admin.setName("xiaohei");
        admin.setPassword("123456");
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
    }
    @Test
    public void testSelectAdmin(){
        Admin admin = new Admin();
        admin.setName("小黑");
        admin.setPassword("123456");
        Admin byName = adminService.login(admin);
        System.out.println(byName);
    }
    @Test
    public void fingAllTurns(){
        List<Turn> allTurns = turnService.findAllTurnsByPage(1, 2);
        for (Turn turn : allTurns) {
            System.out.println(turn);
        }
    }
    @Test
    public void testDeleteTurn(){
        Turn turn = new Turn();
        turn.setId("05be1146-e55b-4d48-b9e4-2130f3a6fdf9");
        turnService.deleteTurn(turn);
    }
    @Test
    public void testFindAllAlbumsByPage(){
        List<Album> albums = albumService.findAllAlbumsByPage(1, 2);
        for (Album album : albums) {
            System.out.println(album);
        }
    }
   /* @Test
    public void testFindChapterByPage(){
        List<Album> chapters = albumMapper.findAlbumAndChaptersByPage(0, 5);
        for (Album chapter : chapters) {
            System.out.println(chapter);
        }
    }*/
   @Test
    public void testAlbumAndChapterByPage(){
       List<Chapter> chapters = chapterMapper.findAlbumAndChapterByPage();
       for (Chapter chapter : chapters) {
           System.out.println(chapter);
       }
   }
   @Test
    public void testDeleteChapter(){
       Chapter chapter = new Chapter();
       chapter.setId("73d932db-210d-49df-b282-dfedcfdf4a47");
       chapterService.deleteChapter(chapter);
   }

  /* @Test
    public void testSentMessage(){
       DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",  "LTAIpWkcx5mM1o4g",  "XPPY35hxaSUyxojqYxZGYcP79cWDhi");
       IAcsClient client = new DefaultAcsClient(profile);
       CommonRequest request = new CommonRequest();
       //request.setProtocol(ProtocolType.HTTPS);
       request.setMethod(MethodType.POST);
       request.setDomain("dysmsapi.aliyuncs.com");
       request.setVersion("2017-05-25");
       request.setAction("SendSms");
       request.putQueryParameter("RegionId", "cn-hangzhou");
       request.putQueryParameter("TemplateCode", "SMS_162522223");
       request.putQueryParameter("SignName", "小蛋黄");
       request.putQueryParameter("PhoneNumbers", "18574258095");
       request.putQueryParameter("TemplateParam", "{\"code\":\"8989\"}");
       try {
           CommonResponse response = client.getCommonResponse(request);
           System.out.println(response.getData());
       } catch (ServerException e) {
           e.printStackTrace();
       } catch (ClientException e) {
           e.printStackTrace();
       }
   }*/

}
