package com.yuanhui.tutorial.network.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientDemo2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
        OutputStream os = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(new File("pepe.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        // 通知服务器，已经结束
        socket.shutdownOutput();

        // 确定服务器接收完毕，才能断开连接
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer2 = new byte[1024];
        int len2;
        while ((len2 = is.read(buffer2)) != -1) {
            baos.write(buffer2, 0, len2);
        }
        System.out.println(baos);

        baos.close();
        is.close();
        fis.close();
        os.close();
        socket.close();
    }
}
