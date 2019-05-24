package com.ahchentong.monitoring.server.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveUDPUtils {
    public static void receive(){
        // 1.建立UDP SOCKET端点 设置监听端口
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(65534);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        // 2.创建数据存放位置并封装
        byte[] bit = new byte[1024];
        DatagramPacket unpack = new DatagramPacket(bit,bit.length);
        // 3.阻塞
        try {
            datagramSocket.receive(unpack);
            System.out.println("IP:" + unpack.getAddress() + ":" + unpack.getPort() + "\nUDP报文> " + byteToStr(unpack));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4.关闭资源
        if (datagramSocket != null){
            datagramSocket.close();
        }
    }

    public static String byteToStr(DatagramPacket unpack){
        DataInputStream istream = new DataInputStream(new ByteArrayInputStream(unpack.getData(), unpack.getOffset(), unpack.getLength()));
        //获取信息
        String msg = null;
        try {
            msg = istream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
