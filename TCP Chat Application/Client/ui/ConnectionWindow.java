/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.*;

/**
 *
 * @author efe44
 */
public class ConnectionWindow extends JFrame implements ActionListener {
    
    private JLabel ipLabel;
    private JTextField ipField;
    private JLabel portLabel;
    private JTextField portField;
    private JButton confirmButton;
    
    private Socket socket;
    
    public ConnectionWindow() {
        this.setTitle("Connection");
        this.setSize(400, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        this.add(getIpLabel());
        this.add(getIpField());
        this.add(getPortLabel());
        this.add(getPortField());
        this.add(getConfirmButton());
        
        this.setVisible(true);
   }

    public JLabel getIpLabel() {
        if(this.ipLabel == null) {
            this.ipLabel = new JLabel("IP: ");
            this.ipLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            this.ipLabel.setBounds(75, 50, 100, 30);
        }
        return ipLabel;
    }

    public JTextField getIpField() {
        if(this.ipField == null) {
            this.ipField = new JTextField();
            this.ipField.setFont(new Font("Arial", Font.PLAIN, 14));
            this.ipField.setBounds(100, 50, 200, 30);
        }
        return ipField;
    }

    public JLabel getPortLabel() {
        if(this.portLabel == null) {
            this.portLabel = new JLabel("Port: ");
            this.portLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            this.portLabel.setBounds(60, 80, 100, 30);
        }
        return portLabel;
    }

    public JTextField getPortField() {
        if(this.portField == null) {
            this.portField = new JTextField();
            this.portField.setFont(new Font("Arial", Font.PLAIN, 14));
            this.portField.setBounds(100, 80, 200, 30);
        }
        return portField;
    }

    public JButton getConfirmButton() {
        if(this.confirmButton == null) {
            this.confirmButton = new JButton("Confirm");
            this.confirmButton.addActionListener(this);
            this.confirmButton.setFocusable(false);
            this.confirmButton.setBounds(150, 120, 100, 30);
        }
        return confirmButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == getConfirmButton()) {
            String ip = getIpField().getText();
            int port = Integer.valueOf(getPortField().getText());
            
            try {
                socket = new Socket(ip, port);
                new NameWindow(socket);
                this.setVisible(false);
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Connection error.");
                ex.printStackTrace();
            }
        }
    }
    
    
}
