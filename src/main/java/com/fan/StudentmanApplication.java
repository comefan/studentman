package com.fan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.fan.dao")
public class StudentmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentmanApplication.class, args);
    }

}
