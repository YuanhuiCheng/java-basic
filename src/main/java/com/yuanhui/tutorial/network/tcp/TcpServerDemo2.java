package com.yuanhui.tutorial.network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket socket = serverSocket.accept(); // 阻塞式监听，一直等待客户端连接
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File("receive.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }

        // 通知客户端接收完毕
        OutputStream os = socket.getOutputStream();
        os.write("finish receiving ...".getBytes());

        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
