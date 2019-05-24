package com.ahchentong.monitoring.server.bean;

public class ReceiveUDPBean {
    private boolean closed;
    private String hostName;
    private String hostAddress;
    private int port;
    private String msg;

    @Override
    public String toString() {
        return "ReceiveUDPBean{" +
                "closed=" + closed +
                ", hostName='" + hostName + '\'' +
                ", hostAddress='" + hostAddress + '\'' +
                ", port=" + port +
                ", msg='" + msg + '\'' +
                '}';
    }

    public ReceiveUDPBean() { }

    public ReceiveUDPBean(boolean closed) {
        this.closed = closed;
    }

    public ReceiveUDPBean(boolean closed, String hostName, String hostAddress, int port, String msg) {
        this.closed = closed;
        this.hostName = hostName;
        this.hostAddress = hostAddress;
        this.port = port;
        this.msg = msg;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
