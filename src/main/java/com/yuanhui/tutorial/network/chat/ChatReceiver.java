package com.yuanhui.tutorial.network.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatReceiver implements Runnable {
    private final int port;
    DatagramSocket socket = null;
    private final String msgFrom;

    public ChatReceiver(int port, String msgFrom) {
        this.port = port;
        this.msgFrom = msgFrom;
        try {
            socket = new DatagramSocket(this.port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] container = new byte[1024];
                // 阻塞式接收包裹
                DatagramPacket packet = new DatagramPacket(container, 0, container.length);
                socket.receive(packet);

                // 断开连接
                byte[] data = packet.getData();
                String receivedData = new String(data, 0, packet.getLength());
                System.out.println(msgFrom + ":" + receivedData);

                if (receivedData.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }
}
