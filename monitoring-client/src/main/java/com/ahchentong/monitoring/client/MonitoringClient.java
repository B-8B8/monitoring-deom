package com.ahchentong.monitoring.client;

import com.ahchentong.monitoring.client.util.ReadProperties;
import com.ahchentong.monitoring.client.util.SendUDPUtils;
import com.ahchentong.monitoring.client.util.ValidationCode;

import java.util.Arrays;

public class MonitoringClient {
    public static void main(String[] args) {
        ReadProperties readProperties = new ReadProperties();
        String[] read = readProperties.read();
        if (read.length >= 2) {
            SendUDPUtils.send(read[0], Integer.parseInt(read[1]), "睡吧猪猪");
            //SendUDPUtils.send(read[0], Integer.parseInt(read[1]), ValidationCode.getValidationCodeForTime());
        }
    }
}
