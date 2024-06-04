/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import Server.Server;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;
/**
 *
 * @author efe44
 */
public class ServerWindow extends JFrame implements ActionListener {
    
    private JPanel panel;
    private JTextArea messageArea;
    private JScrollPane scroll;
    private JButton startServer;
    
    private ServerSocket serverSocket;
    private Server server;
    
    public ServerWindow(int port) {
        this.setTitle("Server");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        
        this.add(getPanel());
        this.add(getStartServer());
        
        this.setVisible(true);
        
        try {
            this.serverSocket = new ServerSocket(port);
            this.server = new Server(serverSocket);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        //this.server.startServer(getMessageArea());
    }

    public JPanel getPanel() {
        if(this.panel == null) {
            this.panel = new JPanel();
            this.panel.setLayout(new BorderLayout());
            this.panel.add(getScroll(), BorderLayout.CENTER);
            this.panel.setBounds(14, 15, 460, 385);
        }
        return panel;
    }

    public JTextArea getMessageArea() {
        if(this.messageArea == null) {
            this.messageArea = new JTextArea();
            this.messageArea.setFont(new Font("Arial", Font.PLAIN, 16));
            this.messageArea.setEditable(false);
        }
        return messageArea;
    }

    public JScrollPane getScroll() {
        if(this.scroll == null) {
            this.scroll = new JScrollPane(getMessageArea());
        }
        return scroll;
    }
    
    public JButton getStartServer() {
        if(this.startServer == null) {
            this.startServer = new JButton("Start");
            this.startServer.addActionListener(this);
            this.startServer.setBounds(200, 410, 100, 40);
        }
        
        return startServer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == getStartServer()) {
            
        }
    }

}
