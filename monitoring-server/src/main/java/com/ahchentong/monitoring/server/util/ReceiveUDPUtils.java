package com.ahchentong.monitoring.server.util;

import com.ahchentong.monitoring.server.bean.ReceiveUDPBean;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveUDPUtils {
    /**
     * 一.建立UDP SOCKET端点 设置监听端口
     * @param port
     * @return
     */
    public static DatagramSocket runDS(int port){
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(port);
            return datagramSocket;
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二.接收数据
     * @param datagramSocket
     */
    public static ReceiveUDPBean receive(DatagramSocket datagramSocket,int byteLength){
        if (datagramSocket == null){
            return new ReceiveUDPBean(false);
        } else {
            // 1.创建数据存放位置并封装
            //byte[] bit = new byte[1048576]; // 1MB
            byte[] bit = new byte[byteLength];
            DatagramPacket unpack = new DatagramPacket(bit,bit.length);
            // 2.阻塞
            try {
                datagramSocket.receive(unpack);
                String msg = byteToStr(unpack);
                if (msg.equals(ValidationCode.getValidationCodeForTime())){
                    return new ReceiveUDPBean(false);
                }
                String hostName = unpack.getAddress().getHostName();
                String hostAddress = unpack.getAddress().getHostAddress();
                int port = unpack.getPort();
                return new ReceiveUDPBean(true,hostName,hostAddress,port,msg);
            } catch (IOException e) {
                e.printStackTrace();
                return new ReceiveUDPBean(false);
            }
        }
    }

    /**
     * 三.关闭资源
     * @param datagramSocket
     */
    public static void closeDS(DatagramSocket datagramSocket){
        if (datagramSocket != null){
            datagramSocket.close();
        }
    }

    public static String byteToStr(DatagramPacket unpack){
        String msg = new String(unpack.getData(),0,unpack.getLength());
        return msg;
    }
}
