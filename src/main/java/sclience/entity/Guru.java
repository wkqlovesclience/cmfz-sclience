package sclience.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@Data
public class Guru {
    @Id
    private String id;

    private String name;

    private String status;

    private String headPic;

    private String describe;

}