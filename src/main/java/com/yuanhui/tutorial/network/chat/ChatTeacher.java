package com.yuanhui.tutorial.network.chat;

public class ChatTeacher {
    public static void main(String[] args) {
        new Thread(new ChatSender(5555, "localhost", 8888)).start();
        new Thread(new ChatReceiver(9999, "student")).start();
    }
}
