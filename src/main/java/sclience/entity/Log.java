package sclience.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@Data
public class Log {
    @Id
    private String id;
    private String operation;//操作
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private String sign;//标记 标记操作是否成功
    private String adminName;
    private Admin admin;//关系属性
}
