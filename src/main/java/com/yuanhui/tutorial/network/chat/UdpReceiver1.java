package com.yuanhui.tutorial.network.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceiver1 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6666);

        while (true) {
            byte[] container = new byte[1024];
            // 阻塞式接收包裹
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            socket.receive(packet);

            // 断开连接
            byte[] data = packet.getData();
            String receivedData = new String(data, 0, packet.getLength());
            System.out.println(receivedData);

            if (receivedData.equals("bye")) {
                break;
            }
        }

        socket.close();
    }
}
