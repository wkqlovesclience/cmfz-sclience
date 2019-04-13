package sclience.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;
@NoArgsConstructor
@Data
public class Lessons {
    @Id
    private String id;

    private String name;

    private String sign;

    private Date createDate;

    private String userId;


}