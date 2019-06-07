package com.ahchentong.socket.rmc;

import com.ahchentong.socket.rmc.util.SocketClientUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //SocketClientUtils.send("localhost",15536,new StringBuffer());
    }
}