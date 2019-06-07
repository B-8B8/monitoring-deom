package com.ahchentong.socket.rms;

import com.ahchentong.socket.rms.bean.socket.SocketInfo;
import com.ahchentong.socket.rms.util.SocketServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
    public static SocketInfo socketInfo;
    public static void main(String[] args) {
        // SpringBoot默认的启动信息
        SpringApplication.run(Application.class, args);
        SocketServerUtils.start(socketInfo.getServerPort());
    }
}
