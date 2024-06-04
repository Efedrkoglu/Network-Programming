/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import Server.Server;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import javax.swing.*;

/**
 *
 * @author efe44
 */
public class PortWindow extends JFrame implements ActionListener {

    private JLabel portLabel;
    private JTextField portField;
    private JButton confirmButton;
    
    public PortWindow() {
        this.setTitle("Port");
        this.setSize(400, 225);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        this.add(getPortLabel());
        this.add(getPortField());
        this.add(getConfirmButton());
        
        this.setVisible(true);
        
    }

    public JLabel getPortLabel() {
        if(this.portLabel == null) {
            this.portLabel = new JLabel("Port: ");
            this.portLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            this.portLabel.setBounds(75, 50, 100, 30);
        }
        return portLabel;
    }

    public JTextField getPortField() {
        if(this.portField == null) {
            this.portField = new JTextField();
            this.portField.setFont(new Font("Arial", Font.PLAIN, 14));
            this.portField.setBounds(110, 50, 200, 30);
        }
        return portField;
    }

    public JButton getConfirmButton() {
        if(this.confirmButton == null) {
            this.confirmButton = new JButton("Confirm");
            this.confirmButton.addActionListener(this);
            this.confirmButton.setFocusable(false);
            this.confirmButton.setBounds(150, 100, 100, 30);
        }
        return confirmButton;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == getConfirmButton()) {
            int port = Integer.valueOf(getPortField().getText());
            if(port > 65535 || port < 0) {
                JOptionPane.showMessageDialog(this, "Port out of range!");
            }
            else {
                this.setVisible(false);
                
                try {
                    ServerSocket ss = new ServerSocket(port);
                    Server server = new Server(ss);
                    server.startServer();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
}
