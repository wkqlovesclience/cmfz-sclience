package sclience.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@Data
public class Album {
    @Id
    private String id;

    private String name;

    private Integer score;

    private String author;

    private String beam;//播音

    private Integer episodes;//集数  章节数量

    private String des;//专辑描述

    private String albumPic;//专辑封面  绝对路径

    private String cover;//封面 相对路径

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;//上传日期

    private String status;

    private String userId;

    //private List<Chapter> chapters;//关系属性


}