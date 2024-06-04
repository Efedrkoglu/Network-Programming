/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.net.*;
import java.io.*;
import javax.swing.JTextArea;

/**
 *
 * @author efe44
 */
public class Server {
    
    private ServerSocket serverSocket;
    private PrintWriter printWriter;
    
    public Server(ServerSocket ss) {
        this.serverSocket = ss;
    }
    
    public void startServer() {
        try {
            while(!serverSocket.isClosed()) {
                Socket s = serverSocket.accept();
                System.out.println("New client connected");
                
                printWriter = new PrintWriter(s.getOutputStream());
                for(ClientHandler ch : ClientHandler.clientHandlers) {
                    printWriter.println(ch.getUserName());
                    printWriter.flush();
                }
                printWriter.println("stop");
                printWriter.flush();
                
                ClientHandler ch = new ClientHandler(s);
                Thread thread = new Thread(ch);
                thread.start();
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
