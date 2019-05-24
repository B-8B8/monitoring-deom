package com.ahchentong.monitoring.client.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ReadProperties {

    /*private static final String CONFIG = "server.ip=localhost" +
            "\n" +
            "server.port=65535" +
            "\n" +
            "local.username=root" +
            "\n" +
            "local.password=?" +
            "\n" +
            "local.host=localhost" +
            "\n" +
            "local.port=22";*/
    private static final String CONFIG = "server.ip=localhost" +
            "\n" +
            "server.port=65535" +
            "\n" +
            "local.username=dev-user" +
            "\n" +
            "local.password=Uforhaoyu" +
            "\n" +
            "local.host=101.207.248.117" +
            "\n" +
            "local.port=6535";
    private static final String FILE_NAME = "monitoring.client.config.properties";
    private static final String SYSTEM_GET_PROPERTY = "path.separator";

    public Properties read() {
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        int firstIndex = path.lastIndexOf(System.getProperty(SYSTEM_GET_PROPERTY)) + 1;
        int lastIndex = path.lastIndexOf(File.separator) + 1;
        path = path.substring(firstIndex, lastIndex);
        File file = new File(path + FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8));
                bufferedWriter.write(CONFIG);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (file.exists()) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(path + FILE_NAME));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
        }
        return null;
    }
}
