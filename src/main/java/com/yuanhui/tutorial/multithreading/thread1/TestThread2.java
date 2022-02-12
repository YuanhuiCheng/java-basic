package com.yuanhui.tutorial.multithreading.thread1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 下载网络图片
 */
public class TestThread2 extends Thread {
    private final String url;
    private final String fileName;

    public TestThread2(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://images.app.goo.gl/Q7uiJsBqQujCiZvn8", "pepe1.jpg");
        TestThread2 t2 = new TestThread2("https://images.app.goo.gl/wpzp4ehZ9vbj8g6F7", "pepe2.jpg");
        TestThread2 t3 = new TestThread2("https://images.app.goo.gl/MfBTR6gs3N8gaLhB8", "pepe3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }

    @Override
    public void run() {
        WebDownloader downloader = new WebDownloader();
        downloader.download(url, fileName);
        System.out.println("file downloaded: " + fileName);
    }
}

// 下载器
class WebDownloader {
    public void download(String url, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("errors occur in downloader");
        }
    }
}
