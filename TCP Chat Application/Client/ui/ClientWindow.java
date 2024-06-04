/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Client.Client;
import java.net.*;

/**
 *
 * @author efe44
 */
public class ClientWindow extends JFrame implements ActionListener {
    
    private JPanel messagePanel;
    private JScrollPane scroll;
    private JTextArea messageArea;
    private JPanel sendMessagePanel;
    private JTextField messageField;
    private JButton sendButton;
    
    private Socket socket;
    private Client client;
    
    public ClientWindow(Socket socket, String userName) {
        this.setTitle(userName + " - Group Chat");
        this.setSize(500, 420);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        this.add(getMessagePanel());
        this.add(getSendMessagePanel());
        
        this.setVisible(true);
        
        try {
            this.socket = socket;
            client = new Client(socket, userName);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        client.listen(getMessageArea());
        client.sendUserName();
    }
    
    public JPanel getMessagePanel() {
        if(this.messagePanel == null) {
            this.messagePanel = new JPanel();
            this.messagePanel.setLayout(new BorderLayout());
            this.messagePanel.add(getScroll(), BorderLayout.CENTER);
            this.messagePanel.setBounds(15, 20, 460, 300);
        }
        return messagePanel;
    }

    public JScrollPane getScroll() {
        if(this.scroll == null) {
            this.scroll = new JScrollPane(getMessageArea());
        }
        return scroll;
    }

    public JTextArea getMessageArea() {
        if(this.messageArea == null) {
            this.messageArea = new JTextArea();
            this.messageArea.setFont(new Font("Arial", Font.PLAIN, 16));
            this.messageArea.setEditable(false);
        }
        return messageArea;
    }

    public JPanel getSendMessagePanel() {
        if(this.sendMessagePanel == null) {
            this.sendMessagePanel = new JPanel();
            this.sendMessagePanel.setLayout(new BorderLayout());
            this.sendMessagePanel.add(getMessageField(), BorderLayout.CENTER);
            this.sendMessagePanel.add(getSendButton(), BorderLayout.EAST);
            this.sendMessagePanel.setBounds(15, 330, 460, 35);
        }
        return sendMessagePanel;
    }

    public JTextField getMessageField() {
        if(this.messageField == null) {
            this.messageField = new JTextField();
            this.messageField.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        return messageField;
    }

    public JButton getSendButton() {
        if(this.sendButton == null) {
            this.sendButton = new JButton("Send");
            this.sendButton.setFocusable(false);
            this.sendButton.addActionListener(this);
        }
        return sendButton;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == getSendButton()) {
            String message = getMessageField().getText();
            getMessageField().setText("");
            client.send(message);
            getMessageArea().append("You: " + message + "\n");
        }
    }
    
}
