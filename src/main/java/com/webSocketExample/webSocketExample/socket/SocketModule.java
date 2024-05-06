package com.webSocketExample.webSocketExample.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import com.webSocketExample.webSocketExample.model.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketModule {

    private final SocketIOServer socketIOServer;

    public SocketModule(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        socketIOServer.addConnectListener(onConnected());
        socketIOServer.addDisconnectListener(onDisconnected());
        socketIOServer.addEventListener("message_api", Message.class,onMessageReceived());
    }

        private DataListener<Message> onMessageReceived() {
         return (senderClient,data,ackSender) -> {
             log.info(String.format("%s -> %s",senderClient.getSessionId(),data.getData()));
             //getBroadcastOperations herkese gonderir
          //   senderClient.getNamespace().getBroadcastOperations().sendEvent("get_message",data.getContent());
             String room = senderClient.getHandshakeData().getSingleUrlParam("channel");

             senderClient.getNamespace().getRoomOperations(room).getClients().forEach(client -> {
                 if(!client.getSessionId().equals(senderClient.getSessionId())){
                     client.sendEvent("get_message",data.getData());
                 }
             } );
         };
        }
    private ConnectListener onConnected() {
        return client -> {
            String room = client.getHandshakeData().getSingleUrlParam("channel");
            client.joinRoom(room);
            client.getNamespace().getRoomOperations(room)
                    .sendEvent("get_message", String.format("%s connected  -> %s",
                            client.getSessionId(), room
                    ));
            log.info(String.format("SocketID: %s connected", client.getSessionId().toString()));
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            String room = client.getHandshakeData().getSingleUrlParam("channel");
            client.getNamespace().getRoomOperations(room)
                    .sendEvent("get_message", String.format("%s disconnected -> %s",
                            client.getSessionId(), room
                    ));
            log.info(String.format("SocketID: %s disconnected!", client.getSessionId().toString()));
        };
    }

}

