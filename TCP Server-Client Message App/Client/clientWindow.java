package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.net.*;
import java.io.*;

public class clientWindow implements ActionListener{
    
    private JFrame frame;
    private JPanel panel1;
    private JTextField textField1;
    private JPanel panel2;
    private JTextField textField2;
    private JButton button;
    private JButton connectButton;
    
    private Socket s = null;
    private PrintWriter pr;
    private BufferedReader br;

    public clientWindow() {
        this.frame = new JFrame("Client");
        this.frame.setSize(600, 400);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.add(this.getPanel1());
        this.frame.add(this.getPanel2());
        this.frame.setVisible(true);
        
        while(true) {
            this.getTextField1().setText(this.receive());
        }
    }
    
    public String receive() {
        String message = "";
        
        try {
            this.br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            message = br.readLine();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return "Server: " + message;
    }

    public JTextField getTextField1() {
        if (this.textField1 == null) {
            this.textField1 = new JTextField();
            this.textField1.setEditable(false);
            this.textField1.setFont(new Font("Arial", Font.PLAIN, 24));
            this.textField1.setHorizontalAlignment(JTextField.CENTER);
            this.textField1.setBounds(0, 15, 600, 200);
        }

        return this.textField1;
    }

    public JPanel getPanel1() {
        if (this.panel1 == null) {
            this.panel1 = new JPanel();
            this.panel1.setLayout(null);
            this.panel1.setBounds(0, 15, 600, 200);
            this.panel1.add(this.getTextField1());
        }

        return this.panel1;
    }
    
    public JTextField getTextField2() {
        if (this.textField2 == null) {
            this.textField2 = new JTextField();
            this.textField2.setFont(new Font("Arial", Font.PLAIN, 16));
            this.textField2.setBounds(40, 250, 400, 40);
        }

        return this.textField2;
    }
    
    public JPanel getPanel2() {
        if (this.panel2 == null) {
            this.panel2 = new JPanel();
            this.panel2.setLayout(null);
            this.panel2.setBounds(40, 250, 400, 40);
            this.panel2.add(this.getTextField2());
            this.panel2.add(this.getButton());
            this.panel2.add(this.getConnectButton());
        }

        return this.panel2;
    }
    
    public JButton getButton() {
        if(this.button == null) {
            this.button = new JButton("Send");
            this.button.addActionListener(this);
            this.button.setBounds(460, 250, 100, 40);
        }
        
        return this.button;
    }
    
    public JButton getConnectButton() {
        if(this.connectButton == null) {
            this.connectButton = new JButton("Connect");
            this.connectButton.addActionListener(this);
            this.connectButton.setBounds(200, 300, 100, 40);
        }
        
        return this.connectButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.getConnectButton()) {
            try {
                this.s = new Socket("localhost", 4444);
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        if(e.getSource() == this.getButton()) {
            String message = this.getTextField2().getText();
            
            try {
                this.pr = new PrintWriter(this.s.getOutputStream());
                pr.println(message);
                pr.flush();
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
