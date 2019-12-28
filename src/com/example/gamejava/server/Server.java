package com.example.gamejava.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private final int timeout;
    private ServerSocket serverSocket;

    public Server(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            return;
        }
        do {
            try {
                final Socket finalAccept = this.serverSocket.accept();
                if (this.timeout > 0) {
                    finalAccept.setSoTimeout(this.timeout);
                }
                Channel.getInstance().createClientHandler(finalAccept);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!this.serverSocket.isClosed());
    }

    public static void main(String[] args) {
        new Server(0).run();
    }
}