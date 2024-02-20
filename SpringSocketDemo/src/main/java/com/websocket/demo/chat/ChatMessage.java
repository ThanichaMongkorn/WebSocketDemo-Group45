package com.websocket.demo.chat;


import lombok.Builder;
import lombok.Getter;

import java.awt.*;
@Getter
@Builder
public class ChatMessage  {
    private String content;
    private String sender;
    private String timestamp;
    private MessageType type;

    //เพิ่มตรงนี้

    private int onlineUser;
    private static int amount;

    public static int joinUser(){
        return ++amount;
    }

    public static int leaveUser(){
        return --amount;
    }

    public void setOnlineUser(int amount){
        onlineUser = amount;
    }
}
