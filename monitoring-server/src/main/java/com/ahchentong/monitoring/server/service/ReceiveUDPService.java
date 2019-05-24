package com.ahchentong.monitoring.server.service;

import com.ahchentong.monitoring.server.bean.ReceiveUDPBean;
import com.ahchentong.monitoring.server.util.ReadProperties;
import com.ahchentong.monitoring.server.util.ReceiveUDPUtils;

import java.net.DatagramSocket;

public class ReceiveUDPService {
    public static void run(){
        ReadProperties readProperties = new ReadProperties();
        String[] read = readProperties.read();
        if (Integer.parseInt(read[0]) >= 1 && Integer.parseInt(read[0]) <= 65535){
            DatagramSocket datagramSocket = ReceiveUDPUtils.runDS(Integer.parseInt(read[0]));
            // 循环使SOCKET一直运行
            ReceiveUDPBean receive = new ReceiveUDPBean(true);
            while (receive.isClosed()){
                receive = ReceiveUDPUtils.receive(datagramSocket);
                if (receive.isClosed()){
                    System.out.println(receive.toString());
                }
            }
            ReceiveUDPUtils.closeDS(datagramSocket);
        }
    }
}
