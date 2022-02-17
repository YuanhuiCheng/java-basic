package com.yuanhui.tutorial.network.url;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlDemo1 {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/helloworld/index.jsp");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPath());
        System.out.println(url.getFile());
        System.out.println(url.getQuery());
    }
}
