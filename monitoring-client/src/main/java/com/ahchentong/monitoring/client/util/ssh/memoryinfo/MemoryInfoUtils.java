package com.ahchentong.monitoring.client.util.ssh.memoryinfo;

import com.ahchentong.monitoring.client.util.ssh.Execute;

public class MemoryInfoUtils {
    public static String getInfo() {
        return Execute.execute("free -h");
    }
}
