package com.yuanhui.tutorial.network.element;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress1 = InetAddress.getByName("127.0.0.1");

            InetAddress inetAddress2 = InetAddress.getByName("www.baidu.com");

            InetAddress inetAddress3 = InetAddress.getByName("localhost");

            InetAddress inetAddress4 = InetAddress.getLocalHost();

            System.out.println(inetAddress2.getAddress());
            System.out.println(inetAddress2.getHostAddress());
            System.out.println(inetAddress2.getCanonicalHostName());
            System.out.println(inetAddress2.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
