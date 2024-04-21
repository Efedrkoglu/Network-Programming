package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class serverWindow implements ActionListener{

    private JFrame frame;
    private JPanel panel1;
    private JTextField textField1;
    private JPanel panel2;
    private JTextField textField2;
    private JButton button;
    
    private ServerSocket ss;
    private Socket s;
    private BufferedReader br;
    private PrintWriter pr;

    public serverWindow() {
        
        try {
            this.ss = new ServerSocket(4444);
            this.s = this.ss.accept();
            
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        this.frame = new JFrame("Server");
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
        String clientMessage = "";
        
        try {
            this.br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            clientMessage = br.readLine();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return "Client: " + clientMessage;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.getButton()) {
            String message = this.getTextField2().getText();
            
            try {
                this.pr = new PrintWriter(this.s.getOutputStream());
                this.pr.println(message);
                this.pr.flush();
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
