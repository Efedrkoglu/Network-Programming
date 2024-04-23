/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author efe44
 */
public class clientWindoww extends JFrame implements ActionListener{
    
    private JFrame parentWindow;
    private int port;
    private String userName;
    
    private JButton exitButton;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel messagesLbl;
    private JLabel log;
    private JTextField textField;
    private JButton sendButton;
    
    private DatagramSocket socket;
    private DatagramPacket outPacket;
    private DatagramPacket inPacket;
    private InetAddress address;
    
    private int timeOutCounter;
    
    public clientWindoww(JFrame pWindow, int port, String userName) {
        this.parentWindow = pWindow;
        this.port = port;
        this.userName = userName;
        this.timeOutCounter = 0;
        
        this.setTitle("Client");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridLayout(1, 2));
        this.add(this.getPanel1());
        this.add(this.getPanel2());
        this.setVisible(true);
        
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            socket.setSoTimeout(3000);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public JButton getExitButton() {
        if(this.exitButton == null) {
            this.exitButton = new JButton("Geri");
            this.exitButton.setBounds(5, 5, 100, 30);
            this.exitButton.addActionListener(this);
        }
        
        return this.exitButton;
    }
    
    public JPanel getPanel1() {
        if(this.panel1 == null) {
            this.panel1 = new JPanel();
            this.panel1.setLayout(null);
            this.panel1.add(this.getExitButton());
            this.panel1.add(this.getTextField());
            this.panel1.add(this.getSendButton());
            this.panel1.add(this.getMessagesLbl());
        }
        
        return this.panel1;
    }
    
    public JPanel getPanel2() {
        if(this.panel2 == null) {
            this.panel2 = new JPanel();
            this.panel2.setLayout(null);
            this.panel2.add(this.getLog());
        }
        
        return this.panel2;
    }
    
    public JLabel getMessagesLbl() {
        if(this.messagesLbl == null) {
            this.messagesLbl = new JLabel("<html></html>");
            this.messagesLbl.setFont(new Font("Arial", Font.PLAIN, 14));
            this.messagesLbl.setBounds(5, 40, 300, 300);
        }
        
        return this.messagesLbl;
    }
    
    public JLabel getLog() {
        if(this.log == null) {
            this.log = new JLabel("<html></html>");
            this.log.setFont(new Font("Arial", Font.PLAIN, 14));
            this.log.setBounds(30, 40, 300, 300);
        }
        
        return this.log;
    }
    
    public JTextField getTextField() {
        if(this.textField == null) {
            this.textField = new JTextField();
            this.textField.setFont(new Font("Arial", Font.PLAIN, 12));
            this.textField.setBounds(10, 400, 240, 30);
        }
        
        return textField;
    }
    
    public JButton getSendButton() {
        if(this.sendButton == null) {
            this.sendButton = new JButton("Send");
            this.sendButton.addActionListener(this);
            this.sendButton.setFont(new Font("Arial", Font.PLAIN, 10));
            this.sendButton.setBounds(260, 400, 60, 30);
        }
        
        return sendButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.exitButton) {
            this.parentWindow.setVisible(true);
            this.dispose();
        }
        
        if(e.getSource() == this.sendButton) {
            try {
                String message = this.userName + ": " + this.getTextField().getText();
                outPacket = new DatagramPacket(message.getBytes(), message.length(), this.address, this.port);
                socket.send(outPacket);

                byte[] buffer = new byte[1024];
                inPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(inPacket);

                String receivedMessage = new String(inPacket.getData(), 0, inPacket.getLength());

                String messages = this.getMessagesLbl().getText();
                messages = messages.substring(0, messages.length() - 7) + "Server: " + receivedMessage + "<br/></html>";

                this.getMessagesLbl().setText(messages);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                
                String log = this.getLog().getText();
                log = log.substring(0, log.length() - 7) + "Error: " + ex.getMessage() + "|" +formattedDateTime + "<br/></html>";
                
                this.getLog().setText(log);
                
                this.timeOutCounter++;
                
                if(this.timeOutCounter >= 3)
                    System.exit(0);
            }
        }
    }
}
