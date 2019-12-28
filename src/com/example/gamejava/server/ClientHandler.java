package com.example.gamejava.server;

import com.example.gamejava.model.entities.Message;
import com.example.gamejava.model.entities.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private List<String> rooms;
    private User user;

    private final Socket acceptSocket;

    public ClientHandler(Socket acceptSocket) throws  Exception {
        this.objectInputStream = new ObjectInputStream(acceptSocket.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(acceptSocket.getOutputStream());
        this.acceptSocket = acceptSocket;
        this.rooms = new ArrayList<>();
        this.start();
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void sendMessage(Message message){
        try{
            this.objectOutputStream.writeObject(message);
        }catch (Exception ex){
            closeAll();
        }
    }

    public void joinRoom(String room){
        if(rooms.indexOf(room)< 0){
            rooms.add(room);
        }
    }

    public List<String> getRooms() {
        return rooms;
    }

    public Socket getAcceptSocket() {
        return acceptSocket;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void run() {
        try {
            while (!this.acceptSocket.isClosed()) {
                Message message = (Message)objectInputStream.readObject();
                Channel.getInstance().handleMessage(message,this);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // When the socket is closed by the com.example.gamejava.client,
            // we throw our own SocketException
            // to break the "keep alive" loop above. If
            // the exception was anything other
            // than the expected SocketException OR a
            // SocketTimeoutException, print the
            // stacktrace

        } finally {
            closeAll();
        }
    }

    private void safeClose(Closeable object){
        try{
            object.close();
        }catch (Exception ex){

        }
    }

    private void closeAll(){
        safeClose(this.objectOutputStream);
        safeClose(this.objectInputStream);
        safeClose(this.acceptSocket);
        Channel.getInstance().clearSocket(this);
    }
}
