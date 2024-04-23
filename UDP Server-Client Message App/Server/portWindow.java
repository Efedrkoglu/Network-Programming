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
 * @author Efe
 */
public class portWindow extends JFrame implements ActionListener{
    
    private JLabel label;
    private JTextField textField;
    private JButton button;
    
    public portWindow() {
        this.setSize(400, 200);
        this.setTitle("Server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(this.getLabel());
        this.add(this.getTextField());
        this.add(this.getButton());
        this.setVisible(true);
    }
    
    public JLabel getLabel() {
        if(this.label == null) {
            this.label = new JLabel("Sunucu port numarasını giriniz:");
            this.label.setFont(new Font("Arial", Font.PLAIN, 14));
            this.label.setBounds(90, 15, 200, 30);
        }
        
        return this.label;
    }
    
    public JTextField getTextField() {
        if(this.textField == null) {
            this.textField = new JTextField();
            this.textField.setFont(new Font("Arial", Font.PLAIN, 14));
            this.textField.setBounds(130, 55, 100, 30);
        }
        
        return this.textField;
    }
    
    public JButton getButton() {
        if(this.button == null) {
            this.button = new JButton("kaydet");
            this.button.addActionListener(this);
            this.button.setBounds(130, 100, 100, 30);
        }
        
        return this.button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.button) {
            int port;
            try {
                port = Integer.parseInt(this.getTextField().getText());
                new serverWindoww(this, port);
                this.setVisible(false);
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gecersiz port numarasi.");
            }
        }
    }
}
