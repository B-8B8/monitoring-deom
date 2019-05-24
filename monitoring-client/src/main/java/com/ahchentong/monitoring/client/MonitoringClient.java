package com.ahchentong.monitoring.client;

import com.ahchentong.monitoring.client.util.ReadProperties;
import com.ahchentong.monitoring.client.util.SendUDPUtils;
import com.ahchentong.monitoring.client.util.ValidationCode;
import com.ahchentong.monitoring.client.util.ssh.diskinfo.DiskInfoUtils;
import com.ahchentong.monitoring.client.util.ssh.memoryinfo.MemoryInfoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MonitoringClient {
    public static void main(String[] args) {
        ReadProperties readProperties = new ReadProperties();
        Properties read = readProperties.read();

        Map<String,Object> msg = new HashMap<>();
        /*msg.put("diskInfo",DiskInfoUtils.getInfo());
        msg.put("memoryInfo",MemoryInfoUtils.getInfo());*/
        msg.put("diskInfo","dghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdghdgh");
        msg.put("memoryInfo","sgfhbn");
        ObjectMapper objectMapper = new ObjectMapper();
        String string = null;
        try {
            string = objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(string);

        SendUDPUtils.send(
                read.getProperty("server.ip"),
                Integer.parseInt(read.getProperty("server.port")),
                string
        );
        //SendUDPUtils.send(read[0], Integer.parseInt(read[1]), ValidationCode.getValidationCodeForTime());
    }
}
