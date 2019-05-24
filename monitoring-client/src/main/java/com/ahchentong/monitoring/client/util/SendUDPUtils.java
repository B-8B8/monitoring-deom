package com.ahchentong.monitoring.client.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class SendUDPUtils {
    public static void send(String host,int port,String msg){
        // 1.建立UDP SOCKET端点
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        // 2.提供数据，封装打包
        //byte[] bit = "正在测试UDP发送 - 客户端".getBytes();
        byte[] bit = strToByteArray(msg);
        DatagramPacket encapsulation = null;
        try {
            //encapsulation = new DatagramPacket(bit,bit.length, InetAddress.getByName("192.168.3.39"),65534);
            encapsulation = new DatagramPacket(bit,bit.length, InetAddress.getByName(host),port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // 3.发送包
        try {
            datagramSocket.send(encapsulation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4.关闭资源
        if (datagramSocket != null){
            datagramSocket.close();
        }
    }

    public static byte[] strToByteArray(String msg){
        /*ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(ostream);
        try {
            dataStream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //要发送的信息字节
        byte[] byteArray = ostream.toByteArray();*/
        byte[] byteArray = msg.getBytes();
        return byteArray;
    }
}
