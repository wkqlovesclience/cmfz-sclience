package sclience.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;
@NoArgsConstructor
@Data
public class Chapter {
    @Id
    private String id;

    private String name;

    private String audioPath;//章节路径

    private Long audioSize;//章节大小

    private String audioTime;//章节时长
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date uploadTime;//上传时间

    private Integer playNum;//播放次数

    private Integer downloadNum;//下载次数

    private String albumId;

    private Album album;//关系属性


}