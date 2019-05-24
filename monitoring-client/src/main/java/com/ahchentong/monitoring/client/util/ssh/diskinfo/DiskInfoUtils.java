package com.ahchentong.monitoring.client.util.ssh.diskinfo;

import com.ahchentong.monitoring.client.util.ssh.Execute;

public class DiskInfoUtils {
    public static String getInfo(){
        return Execute.execute("df -h");
    }
}
