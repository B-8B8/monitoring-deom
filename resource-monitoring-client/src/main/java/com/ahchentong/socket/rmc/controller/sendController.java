package com.ahchentong.socket.rmc.controller;

import com.ahchentong.socket.rmc.util.SocketClientUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sendController {

    @GetMapping(value = "/send")
    public void send (String msg){
        SocketClientUtils.send("localhost",15536,new StringBuffer(msg));
    }

    @GetMapping(value = "/send2")
    public void send2 (String msg){
        SocketClientUtils.send("localhost",15536,new StringBuffer(msg));
    }
}
