package sclience.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@Data
public class Admin {
    @Id
    private String id;

    private String name;

    private String password;


}