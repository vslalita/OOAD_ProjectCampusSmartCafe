package com.csc.GUI;
import java.sql.*; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class Login extends JFrame implements ActionListener{
 
    JPanel pan;
 
    JLabel lblIcon,lblTitle, lblUsername,lblPassword;
    Icon logoImage;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnSubmit,btnReset;
 
    Cursor cSubmit,cReset;
 
    Font ft;
 
    Connection con1;
    PreparedStatement stat1;
    ResultSet rest1;
 
    //LibraryMainMenu lmm;        
    //LibraryReportMenu lrm;
 
    public Login(){
 
        super("Login");
        pan = new JPanel();
        pan.setLayout(null);
 
        logoImage = new ImageIcon("/Users/twinklesiva05/Desktop/images.jpg");
 
        lblIcon = new JLabel(logoImage);
 
        lblTitle = new JLabel("Login");
        ft = new Font("Verdana",Font.BOLD,15);
 
        lblUsername = new JLabel("Username");
        lblPassword =  new JLabel("Password");
 
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        txtPassword.setEchoChar('*');
 
        txtUsername.setToolTipText("Please enter the username");
        txtPassword.setToolTipText("Please enter the Password");
 
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
 
        btnSubmit.setMnemonic(KeyEvent.VK_S);
        btnReset.setMnemonic(KeyEvent.VK_R);
 
        btnSubmit.setRolloverEnabled(true);
        btnSubmit.setMargin(new Insets(20,10,20,10));
 
        pan.setBackground(new Color(255,255,255));
         
        lblTitle.setForeground(new Color(0,50,50));
        lblUsername.setForeground(new Color(0,100,100));
        lblPassword.setForeground(new Color(0,100,100));
 
        btnSubmit.setForeground(new Color(0,170,170));
        btnReset.setForeground(new Color(0,170,170));
 
        cSubmit = btnSubmit.getCursor();
        cReset = btnReset.getCursor();
 
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
 
        lblIcon.setBounds(20,20,377,381);
 
        lblTitle.setBounds(500,100,80,20);
        lblTitle.setFont(ft);
        lblUsername.setBounds(420,130,80,20);
        lblPassword.setBounds(420,170,80,20);
 
        txtUsername.setBounds(510,130,120,20);
        txtPassword.setBounds(510,170,120,20);
 
        btnSubmit.setBounds(460,210,80,20);
        btnSubmit.setToolTipText("Click here to submit the data");
        btnReset.setBounds(550,210,80,20);
        btnReset.setToolTipText("Click here to reset the fields");
 
        btnSubmit.addActionListener(this);
        btnReset.addActionListener(this);
 
        pan.add(lblIcon);
        pan.add(lblUsername);
        pan.add(lblTitle);
        pan.add(lblPassword);
        pan.add(txtUsername);
        pan.add(txtPassword);
        pan.add(btnSubmit);
        pan.add(btnReset);
 
        getContentPane().add(pan);
 
        setSize(650,450);
        setLocation(230,200);
        setVisible(true);
 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent evt){
 
        if(evt.getSource()==btnSubmit){
 
             
        }
 
        if(evt.getSource()==btnReset){
 
 
        }
    }
    public static void main(String args[]){
 
        Login lg = new Login();
    }
}