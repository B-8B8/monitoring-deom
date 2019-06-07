package com.ahchentong.socket.rms.bean.socket;

import com.ahchentong.socket.rms.Application;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "socket.port")
public class SocketInfo {
    private Integer serverPort;
    public Integer getServerPort() {
        return serverPort;
    }
    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }
    @Bean
    public void init(){
        Application.socketInfo = this;
    }
}
