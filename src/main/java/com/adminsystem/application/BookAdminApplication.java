package com.adminsystem.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookAdminApplication.class,args);
    }
}