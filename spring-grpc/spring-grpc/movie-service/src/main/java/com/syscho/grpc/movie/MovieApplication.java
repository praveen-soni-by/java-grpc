package com.syscho.grpc.movie;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieApplication {
    public static void main(String[] args) {

        SpringApplication.run(MovieApplication.class,args);
    }
}
