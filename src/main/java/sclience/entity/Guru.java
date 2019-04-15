package sclience.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@Data
public class Guru {
    @Id
    private String id;

    private String name;//名称

    private String status;//状态

    private String headPic;//头像

    private String describe;//上师简介

}