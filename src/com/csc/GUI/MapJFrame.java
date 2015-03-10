package com.csc.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapJFrame extends JFrame{
	

	public static void main(String args[]){
		new MapJFrame().start();
		//JFrame frame = new JFrame("DefaultButton");
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	public void start(){
		final ImagePanel panel = new ImagePanel(new ImageIcon("/Users/akhilajanapareddy/Desktop/mapImage.png").getImage());
		add(panel);
		setVisible(true);
		setSize(700,700);
		
		
		Icon warnIcon = new ImageIcon("/Users/akhilajanapareddy/Desktop/icon_food.jpg");
		JButton button2 = new JButton(warnIcon);
		button2.setRolloverIcon(warnIcon);
		button2.setBounds(240, 250, 25, 25);
	    panel.add(button2);
	    setVisible(true);
	    button2.setRolloverIcon(warnIcon);
	    final JLabel label1 =  new JLabel();
	    label1.setBounds(220,235,90,10);
	    
	    
	   button2.addMouseListener(new MouseListener(){

		   
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		    panel.add(label1);
		    label1.setForeground(Color.CYAN);
		    label1.setBackground(Color.CYAN);
			label1.setText(String.valueOf("Food-Joint1"));
			
			label1.setVisible(true);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
	     label1.setVisible(false);
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		   
	   });
	  button2.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("button clicked");
			//mouseEntered(e);
		}
		  
	  });
	  
	    Icon warnIcon1 = new ImageIcon("/Users/akhilajanapareddy/Desktop/download.jpg");
		JButton button3 = new JButton(warnIcon1);
		button3.setRolloverIcon(warnIcon1);
		button3.setBounds(150, 300, 25, 25);
		panel.add(button3);
	    setVisible(true);
		
	    Icon warnIcon2 = new ImageIcon("/Users/akhilajanapareddy/Desktop/icon_food.jpg");
		JButton button4 = new JButton(warnIcon2);
		button4.setRolloverIcon(warnIcon2);
		button4.setBounds(340, 450, 25, 25);
	    panel.add(button4);
	    setVisible(true);
	    
	    Icon warnIcon3 = new ImageIcon("/Users/akhilajanapareddy/Desktop/download.jpg");
		JButton button5 = new JButton(warnIcon3);
		button5.setRolloverIcon(warnIcon3);
		button5.setBounds(400, 350, 25, 25);
	    panel.add(button5);
	    setVisible(true);
	    
	    

	    Icon warnIcon4 = new ImageIcon("/Users/akhilajanapareddy/Desktop/icon_food.jpg");
		JButton button6 = new JButton(warnIcon4);
		button6.setRolloverIcon(warnIcon4);
		button6.setBounds(500,275, 25, 25);
	    panel.add(button6);
	    setVisible(true);
	    

	    Icon warnIcon5 = new ImageIcon("/Users/akhilajanapareddy/Desktop/download.jpg");
		JButton button7 = new JButton(warnIcon5);
		button7.setRolloverIcon(warnIcon5);
		button7.setBounds(320, 120, 25, 25);
	    panel.add(button7);
	    setVisible(true);
	    
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// /Users/akhilajanapareddy/Desktop/cups.jpg
		}
	
	
	
}
