package com.worimodoo.baseball.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@Controller
@ServerEndpoint("/websocket")
public class MessageController extends Socket {
    private static final List<Session> session = new ArrayList<Session>();

    @GetMapping("/")
    public String index() {
        // return "index.html";
    	return "websocket_test";
    }

    @OnOpen
    public void open(Session newUser) {
        System.out.println("connected");
        System.out.println("===== 채팅 참여 =====");
        session.add(newUser);
        // System.out.println(newUser.getId());
        System.out.println("newSession newUser getId : " + newUser.getId());
        System.out.println("newSession newUser getBasicRemote : " + newUser.getBasicRemote());
    }
    
    @OnClose
    public void close(Session leaveUser) {
        System.out.println("unConnected");
        System.out.println("===== 채팅 이탈 =====");
        session.add(leaveUser);
        // System.out.println(newUser.getId());
        System.out.println("session leaveUser getId : " + leaveUser.getId());
        System.out.println("session leaveUser getBasicRemote : " + leaveUser.getBasicRemote());
    }
    

    @OnMessage
    public void getMsg(Session recieveSession, String msg) {
        for (int i = 0; i < session.size(); i++) {
            if (!recieveSession.getId().equals(session.get(i).getId())) {
                try {
                    session.get(i).getBasicRemote().sendText("상대 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    session.get(i).getBasicRemote().sendText("나 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}