package com.ahchentong.monitoring.server.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationCode {
    private static MessageDigest md5 = null;
    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getMd5(String str) {
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }

    public static String getValidationCodeForTime() {
        Date k0 = new Date();
        String k1 = new SimpleDateFormat("yyyy").format(k0) + "l";
        String k2 = new SimpleDateFormat("MM").format(k0) + "h";
        String k3 = new SimpleDateFormat("dd").format(k0) + "y";
        String k4 = new SimpleDateFormat("HH").format(k0) + "·Designing·";
        String k5 = new SimpleDateFormat("mm").format(k0);
        String str = k1 + k2 + k3 + k4 + k5;
        return getMd5(str);
    }
}
