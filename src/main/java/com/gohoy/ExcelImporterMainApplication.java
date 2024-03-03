package com.gohoy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gohoy.mapper")
public class ExcelImporterMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelImporterMainApplication.class,args);
    }
}
