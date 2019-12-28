package com.example.gamejava.client;

import com.example.gamejava.model.entities.Account;
import com.example.gamejava.model.entities.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class login {
    public static void main(String[] args) throws IOException {
        try{
            Message message = new Message(Message.TYPE.LOGIN);
            Account account = new Account("a","a");
            message.setAccount(account);

            Socket socket = new Socket("127.0.0.1",6868);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(message);
            out.flush();
        }catch (Exception ex){
            ex.printStackTrace();
            int k =0;
        }

    }
}
