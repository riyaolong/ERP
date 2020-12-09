package com.xlzhou;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xlzhou.system.mapper"})
public class ErplayuispringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErplayuispringbootApplication.class, args);
    }

}
