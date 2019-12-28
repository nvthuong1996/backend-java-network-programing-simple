package com.example.gamejava.server;

import com.example.gamejava.db.ConnectionFactory;
import com.example.gamejava.model.dao.QuestionDAO;
import com.example.gamejava.model.dao.UserDAO;
import com.example.gamejava.model.dao.jdbcimpl.QuestionDaoImpl;
import com.example.gamejava.model.dao.jdbcimpl.UserDaoImpl;
import com.example.gamejava.model.entities.Account;
import com.example.gamejava.model.entities.Message;
import com.example.gamejava.model.entities.Question;
import com.example.gamejava.model.entities.User;
import com.mysql.fabric.xmlrpc.Client;

import java.net.Socket;
import java.util.*;

public class Channel {
    public static Channel instance;
    private List<ClientHandler> clients;

    public Channel() {
        clients = new LinkedList<>();
    }

    public static synchronized Channel  getInstance(){
        if(instance == null){
            instance = new Channel();
        }
        return instance;
    }

    public void createClientHandler(Socket acceptSocket) throws Exception {
        ClientHandler clinet = new ClientHandler(acceptSocket);
        clients.add(clinet);
    }

    public void clearSocket(ClientHandler acceptSocket){
        System.out.println(clients.size());
        clients.remove(acceptSocket);
        System.out.println(clients.size());

    }

    public void handleMessage(Message message,ClientHandler client){
        client.setUser(message.getMe());
        switch (message.getType()){
            case LOGIN:
                handleLogin(message,client);
                break;
            case LIST_USER:
                handleListUser(message,client);
                break;
            case INVITE:
                handleInviteUser(message,client);
                break;
            case ACCEPT_INVITE:
                handleAcceptInvite(message,client);
                break;
            case NOPBAI:
                handleNopbai(message,client);
                break;
        }
    }
    private void handleNopbai(Message message,ClientHandler client){
        try{
            UserDAO dao = new UserDaoImpl(ConnectionFactory.getInstance().getConnection());
            ClientHandler inviter = this.getClientByUser(message.getInviteUser());
            if(inviter != null && message.getMeAnwser() != null){
                QuestionDAO qdao = new QuestionDaoImpl(ConnectionFactory.getInstance().getConnection());
                List<Question> list = qdao.listAll();
                int countPass = 0;
                for(Question a:message.getMeAnwser()){
                    for(Question b:list){
                        if(a.getId() == b.getId() && a.getAnwser() != null && a.getAnwser().equals(b.getAnwser())){
                            countPass ++;
                        }
                    }
                }
                Message send = new Message(Message.TYPE.NOPBAI);
                send.setUser(client.getUser());
                send.setCountPass(countPass);
                inviter.sendMessage(send);
                client.sendMessage(send);
            }else{
                //handle error
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private void handleAcceptInvite(Message message,ClientHandler client){
        try{
            UserDAO dao = new UserDaoImpl(ConnectionFactory.getInstance().getConnection());
            User b = dao.findByUserName(message.getUser().getUsername());
            ClientHandler inviter = this.getClientByUser(message.getUser());

            if(b.getStatus() == User.Status.ONLINE && inviter != null){
                QuestionDAO qdao = new QuestionDaoImpl(ConnectionFactory.getInstance().getConnection());
                List<Question> list = qdao.list(false);

                Message send1 = new Message(Message.TYPE.ACCEPT_INVITE);
                send1.setUser(client.getUser());
                send1.setQuestions(list);
                send1.setInviteUser(b);
                client.sendMessage(send1);

                Message send2 = new Message(Message.TYPE.ACCEPT_INVITE);
                send2.setQuestions(list);
                send2.setUser(b);
                send2.setInviteUser(client.getUser());
                inviter.sendMessage(send2);

            }else{
                Message send = new Message(Message.TYPE.INVITE_ERROR);
                send.setError("Người chơi không online");
                client.sendMessage(send);
                return;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void handleLogin(Message message,ClientHandler client){
        try {
            UserDAO dao = new UserDaoImpl(ConnectionFactory.getInstance().getConnection());
            Account account = message.getAccount();
            User user = dao.findByAccount(account);
            if(client.getAcceptSocket().isClosed()){
                return;
            }
            client.setUser(user);
            Message sendMessage = new Message(Message.TYPE.LOGIN);
            sendMessage.setUser(user);
            client.sendMessage(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleListUser(Message message,ClientHandler client){
        try {
            UserDAO dao = new UserDaoImpl(ConnectionFactory.getInstance().getConnection());
            List<User> list = dao.list(client.getUser());
            if(client.getAcceptSocket().isClosed()){
                return;
            }
            Message sendMessage = new Message(Message.TYPE.LIST_USER);
            sendMessage.setUser(client.getUser());
            sendMessage.setList(list);
            client.sendMessage(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleInviteUser(Message message,ClientHandler client){
        ClientHandler inviter = this.getClientByUser(message.getUser());
//        if(true){
//            Message send = new Message(Message.TYPE.INVITE);
//            send.setUser(message.getUser());
//            client.sendMessage(send);
//            return;
//        }
        if(inviter == null){
            Message send = new Message(Message.TYPE.INVITE_ERROR);
            send.setError("Người chơi không online");
            client.sendMessage(send);
            return;
        }else{
            Message send = new Message(Message.TYPE.INVITE);
            send.setUser(client.getUser());
            inviter.sendMessage(send);
            Message send2 = new Message(Message.TYPE.INVITE_SUCCESS);
            client.sendMessage(send2);
        }
    }

    private ClientHandler getClientByUser(User user){
        for(ClientHandler client:clients){
            if(client.getUser() != null && client.getUser().getUsername().equals(user.getUsername())){
                return client;
            }
        }
        return null;
    }


    private void handleCreateLogin(){

    }

    public int broadCast(String room,Message message){
        return 0;
    }

    public Room getRoom(String room){
        List<ClientHandler> clients = new LinkedList<>();
        for(ClientHandler e : this.clients){
            if(e.getRooms().indexOf(room) >= 0){
                clients.add(e);
            }
        }
        return new Room(clients);
    }
}
