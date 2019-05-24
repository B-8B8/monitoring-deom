package com.ahchentong.monitoring.client.util.ssh;

import com.ahchentong.monitoring.client.util.ReadProperties;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.*;
import java.util.Properties;

public class Execute {
    public static String execute(String command){
        // 返回值
        String str = null;
        //-------------------------------------------------- 连接服务器
        ReadProperties readProperties = new ReadProperties();
        Properties read = readProperties.read();
        Session session = null;
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(
                    read.getProperty("local.username"),
                    read.getProperty("local.host"),
                    Integer.parseInt(read.getProperty("local.port"))
            );
            session.setPassword(read.getProperty("local.password"));

            /*System.out.println("SshPublic信息：");
            System.out.println(read.getProperty("local.username"));
            System.out.println(read.getProperty("local.host"));
            System.out.println(Integer.parseInt(read.getProperty("local.port")));
            System.out.println(read.getProperty("local.password"));*/

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            System.out.println("connect error !");
            return str;
        }
        //-------------------------------------------------- 执行指令
        ChannelExec channelExec = null;
        InputStream inputStream = null;
        try {
            channelExec = (ChannelExec) session.openChannel("exec");
            inputStream = channelExec.getInputStream();
            channelExec.setCommand(command);
            channelExec.connect();

            // 解决了InputStream乱码的问题
            byte[] bit = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len;
            while(-1 != (len = inputStream.read(bit))) {
                byteArrayOutputStream.write(bit, 0, len);
            }
            str = byteArrayOutputStream.toString();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-------------------------------------------------- 关闭一系列需要关闭的东东
            if (channelExec != null){
                channelExec.disconnect();
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {
                session.disconnect();
            }
            return str;
        }
    }
}
