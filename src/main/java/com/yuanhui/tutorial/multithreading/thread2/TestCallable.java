package com.yuanhui.tutorial.multithreading.thread2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class TestCallable implements Callable {
    private final String url;
    private final String fileName;

    public TestCallable(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://images.app.goo.gl/Q7uiJsBqQujCiZvn8", "pepe1.jpg");
        TestCallable t2 = new TestCallable("https://images.app.goo.gl/wpzp4ehZ9vbj8g6F7", "pepe2.jpg");
        TestCallable t3 = new TestCallable("https://images.app.goo.gl/MfBTR6gs3N8gaLhB8", "pepe3.jpg");

        ExecutorService ser = Executors.newFixedThreadPool(3);

        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        ser.shutdown();
    }

    @Override
    public Boolean call() {
        WebDownloader downloader = new WebDownloader();
        downloader.download(url, fileName);
        System.out.println("file downloaded: " + fileName);
        return true;
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
