package sclience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "sclience.dao")
public class CmfzSclienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzSclienceApplication.class, args);
    }

}
