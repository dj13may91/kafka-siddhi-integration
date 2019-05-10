package com.example.siddhi;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiddhiApplication {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        SpringApplication.run(SiddhiApplication.class, args);
    }

}
