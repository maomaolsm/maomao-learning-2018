package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.run(args);
    }

    public void sort() {
        List<Integer> ljh1 = new ArrayList<Integer>();
        List<Integer> ljh2 = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            ljh1.add((int) (Math.random() * 90 + 10));
            ljh2.add((int) (Math.random() * 90 + 10));
        }

        Collections.sort(ljh1);
        Collections.sort(ljh2);
        System.out.println(ljh1.toString());
        System.out.println(ljh2.toString());
        System.out.println("------------------");
        Collections.shuffle(ljh1);
        Collections.shuffle(ljh2);
        System.out.println(ljh1.toString());
        System.out.println(ljh2.toString());
    }
}
