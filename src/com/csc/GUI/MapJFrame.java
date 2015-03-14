package com.csc.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.csc.CSCApplication;
import com.csc.CurrentSession;
import com.csc.FoodJointService;

public class MapJFrame extends JFrame{


	public static void main(String args[]){
		new MapJFrame().start();
		//JFrame frame = new JFrame("DefaultButton");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start(){
		final ImagePanel panel = new ImagePanel(new ImageIcon("/Users/twinklesiva05/Desktop/mapImage.png").getImage());
		add(panel);
		setVisible(true);
		setSize(700,700);
		
		CSCApplication app=new CSCApplication();
		ArrayList<FoodJointService> foodJoints=app.getAllFoodJoints();
		JButton machineButton;
		for(int i=0;i<foodJoints.size();i++){
			final FoodJointService foodJoint=foodJoints.get(i);
			if(foodJoint.getIsCafe()){
				Icon vendingMachineIcon = new ImageIcon("/Users/twinklesiva05/Desktop/icon_food.jpg");
				machineButton = new JButton(vendingMachineIcon);
				machineButton.setRolloverIcon(vendingMachineIcon);
				machineButton.setBounds(foodJoint.getXPostion(), foodJoint.getYPostion(), 25, 25);
				final JLabel machineDetailsLabel=new JLabel("Cafe");
				final JLabel machineLocationLabel=new JLabel("Location:"+foodJoint.getLocation());
				machineDetailsLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-30,150,10);
				machineLocationLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-10,250,10);
				machineButton.addMouseListener(new MouseListener(){


					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub

						panel.add(machineDetailsLabel);
						panel.add(machineLocationLabel);
						machineLocationLabel.setVisible(true);
						machineDetailsLabel.setVisible(true);
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						machineDetailsLabel.setVisible(false);
						machineLocationLabel.setVisible(false);

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
				machineButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						CurrentSession.setMachine(foodJoint);
						System.out.println("Created Cafe");
						LoginGUI openLoginFrame=new LoginGUI();
					}
					
				});
				
				panel.add(machineButton);
				
			}
			if(foodJoint.getIsVendingMachine()){
				Icon vendingMachineIcon = new ImageIcon("/Users/twinklesiva05/Desktop/download.jpg");
				machineButton = new JButton(vendingMachineIcon);
				machineButton.setRolloverIcon(vendingMachineIcon);
				machineButton.setBounds(foodJoint.getXPostion(), foodJoint.getYPostion(), 25, 25);
				final JLabel machineDetailsLabel=new JLabel("VendingMachine");
				final JLabel machineLocationLabel=new JLabel("Location:"+foodJoint.getLocation());
				machineDetailsLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-30,150,10);
				machineLocationLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-10,250,10);
				machineButton.addMouseListener(new MouseListener(){


					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub

						panel.add(machineDetailsLabel);
						panel.add(machineLocationLabel);
						machineLocationLabel.setVisible(true);
						machineDetailsLabel.setVisible(true);
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						machineDetailsLabel.setVisible(false);
						machineLocationLabel.setVisible(false);

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
				
				machineButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						CurrentSession.setMachine(foodJoint);
						System.out.println("Created Vending Machine");
						LoginGUI openLoginFrame=new LoginGUI();
					}
					
				});
				
				panel.add(machineButton);
			}
			
			
			setVisible(true);
		}
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// /Users/akhilajanapareddy/Desktop/cups.jpg
	}
}
