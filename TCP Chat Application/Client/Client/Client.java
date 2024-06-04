/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 *
 * @author efe44
 */
public class Client {
    
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String userName;
    
    public Client(Socket s, String userName) {
        try {
            this.socket = s;
            this.printWriter = new PrintWriter(s.getOutputStream());
            this.bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.userName = userName;
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            closeSocket();
        }
    }
    
    public void sendUserName() {
        try {
            printWriter.println(userName);
            printWriter.flush();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void send(String message) {
        try {
            printWriter.println(userName + ": " + message);
            printWriter.flush();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            closeSocket();
        }
    }
    
    public void listen(JTextArea messageArea) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                
                try {
                    while(socket.isConnected()) {
                        message = bufferedReader.readLine();
                        messageArea.append(message + "\n");
                    }
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                    closeSocket();
                }
            }            
        }).start();
    }
    
    public void closeSocket() {
        try {
            if(socket != null) {
                socket.close();
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());      
        }
    }
}
