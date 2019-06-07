package com.ahchentong.socket.rmc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClientUtils {
    protected static final Logger logger = LoggerFactory.getLogger(SocketClientUtils.class);
    private static Socket socket;
    private static OutputStream outputStream;
    private static byte[] bytes;

    public static void send(String host, int port, StringBuffer message) {
        // 第一次进入或断开连接后会进入此处
        try {
            if (socket == null || socket.isClosed()) {
                socket = new Socket(host, port);
            }
        } catch (IOException e) {
            logger.error("Socket连接错误");
            //e.printStackTrace();
        }
        // 获取输出流
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            logger.error("获取输出流错误");
            //e.printStackTrace();
        }
        // 清空复用的bytes
        bytes = null;
        // 转换为和客户端协商一致的UTF-8编码
        bytes = message.toString().getBytes(StandardCharsets.UTF_8);
        // 判断bytes是否有内容
        if (bytes != null && bytes.length > 0) {
            // 获取bytes的长度并转换为字符串
            String byteStr = String.valueOf(bytes.length);
            // 获取补0次数
            int time = 8 - byteStr.split("").length;
            if (time >= 1 && time <= 8) {
                while (time-- > 0) {
                    byteStr = "0" + byteStr;
                }

                try {
                    // 发送第一遍消息，将信息长度用8byte的数据先传过去
                    outputStream.write(byteStr.getBytes(StandardCharsets.UTF_8));
                    // 发送消息
                    outputStream.write(bytes);
                    // 发送
                    outputStream.flush();

                } catch (IOException e) {
                    try {
                        socket = new Socket(host, port);
                    } catch (IOException ex) {
                        logger.error("Socket连接错误");
                        //ex.printStackTrace();
                    }
                    //e.printStackTrace();
                }
            } else {
                logger.error("message超过99999999(byte)。");
            }
        } else {
            logger.error("message转换byte时数据为空。");
        }
        /*outputStream.close();
        socket.close();*/
    }
}
