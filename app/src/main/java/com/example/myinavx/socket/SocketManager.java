package com.example.myinavx.socket;

import com.example.myinavx.socket.tcp.TcpTask;
import com.example.myinavx.socket.udp.UdpTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anna on 2017/4/21.
 */

public class SocketManager {

    private static final SocketManager sINSTANCE = new SocketManager();

    private static Map<String, Object> socketMap = new HashMap<>();

    private SocketManager() {
    }

    public static SocketManager getInstance() {
        return sINSTANCE;
    }

    public void createTcpConnection(String host, int port) {
        TcpTask tcpTask = new TcpTask(host, port);
        socketMap.put(host + File.separator + port, tcpTask);
        tcpTask.execute();
    }

    public void closeTcpConnection(String host, int port) {
        if(null!=socketMap.get(host+File.separator+port)){
            ((TcpTask)socketMap.get(host+File.separator+port)).cancel(true);
        }
    }

    public void createUdpConnection(int port) {
        UdpTask udpTask = new UdpTask(port);
        socketMap.put(File.separator+port,udpTask);
        udpTask.execute();
    }

    public void closeUdpConnection(int port) {
        if(null!=socketMap.get(File.separator+port)){
            ((UdpTask)socketMap.get(File.separator+port)).cancel(true);
        }
    }
}
