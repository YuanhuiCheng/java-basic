package com.yuanhui.tutorial.network.chat;

public class ChatStudent {
    public static void main(String[] args) {
        new Thread(new ChatSender(7777, "localhost", 9999)).start();
        new Thread(new ChatReceiver(8888, "teacher")).start();
    }
}
