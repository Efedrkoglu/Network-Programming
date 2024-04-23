/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author efe44
 */
public class portWindow extends JFrame implements ActionListener {
    
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;
    
    public portWindow() {
        this.setTitle("Client");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.add(this.getLabel1());
        this.add(this.getLabel2());
        this.add(this.getTextField1());
        this.add(this.getTextField2());
        this.add(this.getButton());
        this.setVisible(true);
    }

    public JLabel getLabel1() {
        if(this.label1 == null) {
            this.label1 = new JLabel("Kullanıcı adı: ");
            this.label1.setFont(new Font("Arial", Font.PLAIN, 14));
            this.label1.setBounds(20, 20, 100, 30);
        }
        
        return label1;
    }

    public JLabel getLabel2() {
        if(this.label2 == null) {
            this.label2 = new JLabel("Port: ");
            this.label2.setFont(new Font("Arial", Font.PLAIN, 14));
            this.label2.setBounds(20, 50, 100, 30);
        }
        
        return label2;
    }

    public JTextField getTextField1() {
        if(this.textField1 == null) {
            this.textField1 = new JTextField();
            this.textField1.setFont(new Font("Arial", Font.PLAIN, 14));
            this.textField1.setBounds(120, 20, 200, 30);
        }
        
        return textField1;
    }

    public JTextField getTextField2() {
        if(this.textField2 == null) {
            this.textField2 = new JTextField();
            this.textField2.setFont(new Font("Arial", Font.PLAIN, 14));
            this.textField2.setBounds(120, 50, 200, 30);
        }
        
        return textField2;
    }

    public JButton getButton() {
        if(this.button == null) {
            this.button = new JButton("Kaydet");
            this.button.addActionListener(this);
            this.button.setBounds(120, 90, 100, 30);
            
        }
        return button;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.button) {
            int port;
            
            try {
                port = Integer.parseInt(this.getTextField2().getText());
                String userName = this.getTextField1().getText();
                new clientWindoww(this, port, userName);
                this.setVisible(false);
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Gecersiz port");
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
