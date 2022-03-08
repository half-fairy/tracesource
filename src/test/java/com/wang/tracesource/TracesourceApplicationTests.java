package com.wang.tracesource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootTest
class TracesourceApplicationTests {


    @Test
    void contextLoads() {

        Queue<Integer> queue=new LinkedList<>();
        System.out.println(queue.offer(5));

    }

}
