package sclience.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;
@NoArgsConstructor
@Data
public class Article {
    @Id
    private String id;

    private String name;//文章名称

    private String content;//文章内容

    private String articlePic;//文章图片

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;//发布日期

    private Integer readNum;//阅读次数

    private String guruId;//上师id

    private Guru guru;//关系属性


}