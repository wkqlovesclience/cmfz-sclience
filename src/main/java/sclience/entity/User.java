package sclience.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;
@NoArgsConstructor
@Data
public class User {
    @Id
    private String id;

    private String userName;//用户名

    private String password;

    private String phone;

    private String sex;

    private String nickName;//昵称 法名

    private String name;//真实姓名

    private String province;//所在省份

    private String city;//所在城市

    private String autograph;//个人签名

    private String headPic;//头像

    private String status;//状态

    private String salt;//盐
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registTime;//注册时间

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastloginTime;//最后登录时间


}