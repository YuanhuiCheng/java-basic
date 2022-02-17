package com.yuanhui.tutorial.network.url;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlDownload {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8080/yuanhui/security.txt");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = conn.getInputStream();
        FileOutputStream fos = new FileOutputStream("securityNewFile.txt");

        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        inputStream.close();
        conn.disconnect();
    }
}
