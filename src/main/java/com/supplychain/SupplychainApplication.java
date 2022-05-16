package com.supplychain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.supplychain.dao"})
public class SupplychainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplychainApplication.class, args);
    }

}
