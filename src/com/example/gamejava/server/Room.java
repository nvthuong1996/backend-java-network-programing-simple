package com.example.gamejava.server;

import com.example.gamejava.model.entities.Message;

import java.util.List;

public class Room {
    private List<ClientHandler> clients;

    public Room(List<ClientHandler> clients) {
        this.clients = clients;
    }
    public void send(Message message){
        for(ClientHandler e: clients){
            e.sendMessage(message);
        }
    }
}
