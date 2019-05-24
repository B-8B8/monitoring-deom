package com.ahchentong.monitoring.server;

import com.ahchentong.monitoring.server.service.ReceiveUDPService;

public class MonitoringServer {
    public static void main(String[] args) {
        ReceiveUDPService.run();
    }
}
