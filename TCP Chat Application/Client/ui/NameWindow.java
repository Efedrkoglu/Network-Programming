/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author efe44
 */
public class NameWindow extends JFrame implements ActionListener {
    
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton confirmButton;
    
    private ArrayList<String> nameList;
    private Socket socket;
    private BufferedReader bufferedReader;
    
    public NameWindow(Socket s) {
        this.setTitle("Name");
        this.setSize(400, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        this.add(getNameLabel());
        this.add(getNameField());
        this.add(getConfirmButton());
        
        this.setVisible(true);
        
        nameList = new ArrayList<>();
        String nameFromServer = "";
        try {
            this.socket = s;
            this.bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            while(!nameFromServer.equals("stop")) {
                nameFromServer = bufferedReader.readLine();
                if(!nameFromServer.equals("stop")) {
                    nameList.add(nameFromServer);
                }
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public JLabel getNameLabel() {
        if(this.nameLabel == null) {
            this.nameLabel = new JLabel("User Name: ");
            this.nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            this.nameLabel.setBounds(40, 50, 100, 30);
        }
        return nameLabel;
    }

    public JTextField getNameField() {
        if(this.nameField == null) {
            this.nameField = new JTextField();
            this.nameField.setFont(new Font("Arial", Font.PLAIN, 14));
            this.nameField.setBounds(120, 50, 200, 30);
        }
        return nameField;
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
            String userName = getNameField().getText();
            
            if(userName.length() >= 5) {
                int kontrol = 0;
                for(String namesFromServer : nameList) {
                    if(userName.toUpperCase().equals(namesFromServer.toUpperCase()))
                        kontrol = 1;
                }
                
                if(kontrol == 0) {
                    new ClientWindow(socket, userName);
                    this.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(this, "This username has taken.");
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Username length must be at least 5 characters!");
            }
        }
    }
    
}
