package com.ahchentong.monitoring.server.service;

import com.ahchentong.monitoring.server.bean.ReceiveUDPBean;
import com.ahchentong.monitoring.server.util.ReadProperties;
import com.ahchentong.monitoring.server.util.ReceiveUDPUtils;

import java.net.DatagramSocket;
import java.util.Properties;

public class ReceiveUDPService {
    public static void run(){
        ReadProperties readProperties = new ReadProperties();
        Properties read = readProperties.read();
        String port = read.getProperty("bind.port");
        String byteLength = read.getProperty("byte.length");
        if (Integer.parseInt(port) >= 1 && Integer.parseInt(port) <= 65535){
            DatagramSocket datagramSocket = ReceiveUDPUtils.runDS(Integer.parseInt(port));
            // 循环使SOCKET一直运行
            ReceiveUDPBean receive = new ReceiveUDPBean(true);
            while (receive.isClosed()){
                receive = ReceiveUDPUtils.receive(datagramSocket,Integer.parseInt(byteLength));
                if (receive.isClosed()){
                    System.out.println(receive.toString());
                    //System.out.println(receive.getMsg());
                }
            }
            ReceiveUDPUtils.closeDS(datagramSocket);
        }
    }
}
