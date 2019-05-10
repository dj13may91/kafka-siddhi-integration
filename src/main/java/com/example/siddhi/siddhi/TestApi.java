package com.example.siddhi.siddhi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @Autowired
    SiddhiConfiguration configuration;

    @RequestMapping(path = "/health")
    public ResponseEntity<String> health(){
        return new ResponseEntity<>( "working", HttpStatus.OK);
    }

    @RequestMapping(path = "/data/{num}")
    public void siddhiTest(@PathVariable long num){
        System.out.println(num);
    }
}
