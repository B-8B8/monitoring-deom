package com.ahchentong.socket.rms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerUtils {
    protected static final Logger logger = LoggerFactory.getLogger(SocketServerUtils.class);
    private static Socket socket;
    private static ServerSocket serverSocket;
    public static void start(int port) {
        // 开始监听端口
        try {
            serverSocket = new ServerSocket(port);
            logger.info("端口启动在：" + port + "端口。");
        } catch (IOException e) {
            logger.error("端口监听失败，请检查：" + port + "端口。");
            //e.printStackTrace();
        }
        logger.info("服务器运行...");

        // 使用多线程并创建线程池防止高并发创建过多线程导致资源耗尽
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while (true) {
            //====================================================================================================
            try {
                socket = serverSocket.accept();
            } catch (IOException e){
                logger.error("客户连接失败...");
                //e.printStackTrace();
            }
            Runnable runnable = () -> {
                byte[] bytes = null;
                InputStream inputStream = null;
                try {
                    // 获取socket中的输入流，建立缓冲区进行读取
                    inputStream = socket.getInputStream();
                } catch (IOException e) {
                    logger.error("获取输入流失败...");
                    //e.printStackTrace();
                }
                logger.info("收到客户端消息：" + socket);
                // 循环读取输出流
                while (true) {
                    try {
                        // 约定第一次消息长度为8byte，用于记录消息的长度
                        bytes = new byte[8];
                        // 读取第一次消息，获得消息的长度
                        inputStream.read(bytes);
                    } catch (IOException e) {
                        logger.error("获取第一次消息失败...");
                        //e.printStackTrace();
                    }
                    try {
                        String message_str = new String(bytes,"UTF-8");
                        int message_length = Integer.parseInt(message_str);
                        bytes = new byte[message_length];
                        // 读取第二次消息，获得消息的长度
                        inputStream.read(bytes);
                        System.out.println(new String(bytes,"UTF-8"));
                    } catch (IOException e) {
                        logger.error("获取第二次消息失败...");
                        //e.printStackTrace();
                    }
                }
                /*try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            };
            // 提交线程进入线程池
            threadPool.submit(runnable);
            //====================================================================================================
        }
    }
}
