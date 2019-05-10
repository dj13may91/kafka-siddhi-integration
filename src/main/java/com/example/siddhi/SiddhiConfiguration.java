package com.example.siddhi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiddhiConfiguration {

    @Autowired
    ObjectDispatcher dispatcher;


    public void output(long num) {

        //Sending events to Siddhi
        try {
            dispatcher.getInputHandler().send(new Object[]{num});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
