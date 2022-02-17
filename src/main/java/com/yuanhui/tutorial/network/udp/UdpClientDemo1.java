package com.yuanhui.tutorial.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClientDemo1 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String msg = "hello, server";
        InetAddress localhost = InetAddress.getByName("localhost");
        int port = 9090;
        DatagramPacket packet = new DatagramPacket(msg.getBytes(),
                0, msg.getBytes().length, localhost, port);
        socket.send(packet);

        socket.close();
    }
}
