package com.example.Serialization;

import com.example.kafka.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PersonDeserializer implements Deserializer {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Person person = null;
        try {
            person = mapper.readValue(s, Person.class);
            System.out.println("de-serializing successful for person: " + person);
        } catch (Exception e) {
            System.out.println("Error de-serializing person: " + bytes);
            System.out.println("Error: " + e.getMessage());
        }
        return person;
    }

    @Override
    public void close() {

    }
}
