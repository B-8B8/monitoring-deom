package com.ahchentong.monitoring.server;

import com.ahchentong.monitoring.server.util.ReceiveUDPUtils;

public class MonitoringServer {
    public static void main(String[] args) {
        while (true){
            ReceiveUDPUtils.receive();
        }
    }
}
