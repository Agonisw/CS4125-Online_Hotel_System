package com.xxx.common.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/bindingRecord")
@Component
@Slf4j
public class WebSocket {

    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    /**
     * Create a new webSocket configuration class
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    /**
     * connection establishment
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【connection establishment】，connectionNumber:{}", webSockets.size());
    }

    /**
     * Interrupt
     */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【Interrupt 】，connectionNumber:{}", webSockets.size());
    }

    /**
     * Receive Info
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        log.info("【Receive】，Information:{}，connectionNumber:{}", message, webSockets.size());
    }

    /**
     * Send message
     * @param message
     */
    public void sendMessage(String message){
        log.info("【BroadcastFlag】，Information:{}，connectionNumber:{}", message, webSockets.size());
        for (WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.info("【BroadcastFlag】，Information abnormity:{}", e.fillInStackTrace());
            }
        }
    }

}
