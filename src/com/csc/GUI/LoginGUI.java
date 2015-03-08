package com.csc.GUI;

import javax.swing.*;

import com.csc.CurrentSession;

import java.awt.*;
import java.awt.event.*;
/**
 * 
 * @author Akhila Janapreddy
 *
 */
public class LoginGUI extends JFrame {

	JButton blogin = new JButton("Enter");
	JPanel panel = new JPanel();
	JTextField cardNumberTextField = new JTextField(15);

	LoginGUI(){
		super("Login Authentication");
		setSize(300,200);
		setLocation(500,280);
		panel.setLayout (null); 


		cardNumberTextField.setBounds(70,30,150,20);
		blogin.setBounds(110,100,80,20);

		panel.add(blogin);
		panel.add(cardNumberTextField);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin(){
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String pcode = cardNumberTextField.getText();
				if(pcode.matches("[0-9]+") && pcode.length()==5) {
					 CurrentSession.getMemberInstance(pcode); // create the user for this session.
					 if(CurrentSession.getCurrentUser()!=null){
						 JFrame homeFrame =new HomPageGUI();
						 homeFrame.setVisible(true);
						 dispose();
					 }
				} 
				else {
					JOptionPane.showMessageDialog(null,"Wrong Passcode");
					cardNumberTextField.setText("");
					cardNumberTextField.requestFocus();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		LoginGUI frameTabel = new LoginGUI();
	}
	
}
