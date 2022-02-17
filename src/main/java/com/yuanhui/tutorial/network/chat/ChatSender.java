package com.yuanhui.tutorial.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ChatSender implements Runnable {
    private final int fromPort;
    private final String toIp;
    private final int toPort;
    DatagramSocket socket = null;
    BufferedReader reader = null;

    public ChatSender(int fromPort, String toIp, int toPort) {
        this.fromPort = fromPort;
        this.toIp = toIp;
        this.toPort = toPort;

        try {
            socket = new DatagramSocket(fromPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = reader.readLine();
                byte[] bytes = data.getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                        new InetSocketAddress(this.toIp, this.toPort));
                socket.send(packet);

                if (data.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }
}
