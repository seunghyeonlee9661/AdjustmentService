package com.sparta;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

    public void dependencyTest() {
        System.out.println("Common is Loaded!");
    }

}