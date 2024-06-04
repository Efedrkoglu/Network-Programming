/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author efe44
 */
public class ClientHandler implements Runnable {
    
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String userName;
    
    public ClientHandler(Socket s) {
        try {
            this.socket = s;
            this.printWriter = new PrintWriter(s.getOutputStream());
            this.bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.userName = bufferedReader.readLine();
            clientHandlers.add(this);
            sendMessage("SERVER: " + userName + " joined to the chat.");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            closeSocket();
        }
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    @Override
    public void run() {
        String message;
        
        try {
            while(socket.isConnected()) {
                message = bufferedReader.readLine();
                sendMessage(message);
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            closeSocket();
        }
    }
    
    public void sendMessage(String message) {
        try {
            for(ClientHandler ch : clientHandlers) {
                if(!ch.userName.equals(userName)) {
                    ch.printWriter.println(message);
                    ch.printWriter.flush();
                }
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            closeSocket();
        }
    }
    
    public void removeClient() {
        sendMessage("SERVER: " + userName + " left from the chat");
        clientHandlers.remove(this);
    }
    
    public void closeSocket() {
        removeClient();
        try {
            if (socket != null) {
                socket.close();
            }
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
