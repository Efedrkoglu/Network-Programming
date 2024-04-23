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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author efe44
 */
public class serverWindoww extends JFrame implements ActionListener{

    private JFrame parentWindow;
    private int port;
    
    private JButton exitButton;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel messagesLbl;
    private JButton begin;
    
    private DatagramSocket socket;
    private DatagramPacket inPacket;
    private DatagramPacket outPacket;
    private InetAddress address;
    private int clientPort;
    
    public serverWindoww(JFrame pWindow, int port) {
        this.parentWindow = pWindow;
        this.port = port;
        System.out.println(port);
        
        this.setTitle("Server");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridLayout(1, 1));
        this.add(this.getPanel1());
        this.setVisible(true);
        
        try {
            socket = new DatagramSocket(this.port);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void communicate() {
        try {
            byte[] buffer = new byte[1024];
            inPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(inPacket);
            
            address = inPacket.getAddress();
            clientPort = inPacket.getPort();

            String receivedMessage = new String(inPacket.getData(), 0, inPacket.getLength());

            String messages = this.getMessagesLbl().getText();
            messages = messages.substring(0, messages.length() - 7) + address + ":" + clientPort + "#" + receivedMessage + "<br/></html>";

            this.getMessagesLbl().setText(messages);

            String answer = "message: '" + receivedMessage + "' received";
            outPacket = new DatagramPacket(answer.getBytes(), answer.length(), address, clientPort);
            socket.send(outPacket);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public JButton getBegin() {
        if(this.begin == null) {
            this.begin = new JButton("Begin");
            this.begin.addActionListener(this);
            this.begin.setBounds(110, 5, 100, 30);
        }
        
        return this.begin;
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
            this.panel1.add(this.getBegin());
            this.panel1.add(this.getMessagesLbl());
        }
        
        return this.panel1;
    }
    
    public JPanel getPanel2() {
        if(this.panel2 == null) {
            this.panel2 = new JPanel();
            this.panel2.setLayout(null);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.exitButton) {
            this.parentWindow.setVisible(true);
            this.dispose();
        }
        
        if(e.getSource() == this.begin) {
            this.communicate();
        }
    }
    
}
