package com.csc.GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
class BackgroundImageJFrame extends JFrame
{
	JButton b1;
	JLabel l1;
	JButton blogin = new JButton("Enter");
	JPanel panel = new JPanel();
	JTextField cardNumberTextField = new JTextField(15);
	//private Image background;
	public BackgroundImageJFrame()
	{
	setTitle("Background Color for JFrame");
	setSize(4928,3264);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
	setLayout(new BorderLayout());
	File dir1 = new File ("/Users/twinklesiva05/Desktop/images.jpg");
	JLabel background = new JLabel(new ImageIcon(dir1.getAbsolutePath()));

	background.setBounds(0, 0, 1200, 1300);
	add(background);
	background.setLayout(new FlowLayout());
	
	cardNumberTextField.setBounds(70,30,150,20);
	blogin.setBounds(110,100,80,20);
	add(cardNumberTextField);
	add(blogin);
	
	}
	public static void main(String args[])
	{
		new BackgroundImageJFrame();
	}
	
}