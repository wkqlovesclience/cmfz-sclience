package sclience.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
public class Counter {
    private String id;

    private String name;

    private Integer num;

    private Date createDate;

    private Date lastuseTime;

    private String userId;

    private String lessonsId;


}