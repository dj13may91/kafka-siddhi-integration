package com.example.Serialization;

import com.example.kafka.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PersonSerializer implements Serializer<Person> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Person person) {
        byte[] personBytes = null;

        ObjectMapper mapper = new ObjectMapper();

        try{
            personBytes = mapper.writeValueAsString(person).getBytes();
            System.out.println("serializing successful for person: " + person);
        } catch (JsonProcessingException e) {
            System.out.println("Error serializing person: " + person);
            System.out.println("Error: " + e.getMessage());
        }

        return personBytes;
    }

    @Override
    public void close() {

    }
}
