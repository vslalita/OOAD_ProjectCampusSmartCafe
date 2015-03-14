package com.csc.GUI;
/**
 * author: Akhila Janapareddy
 * 
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.csc.model.FoodJoint;

public class MapJFrame extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]){
		new MapJFrame().start();
	}

	public void start(){
		final ImagePanel panel = new ImagePanel(new ImageIcon("/Users/twinklesiva05/Desktop/mapImage.png").getImage());
		
		CSCApplication app=new CSCApplication();
		ArrayList<FoodJoint> foodJoints=app.getAllFoodJoints();
		JButton machineButton;
		for(int i=0;i<foodJoints.size();i++){
			final FoodJoint foodJoint=foodJoints.get(i);
			if(foodJoint.getIsCafe()){
				Icon vendingMachineIcon = new ImageIcon("/Users/twinklesiva05/Desktop/icon_food.jpg");
				machineButton = new JButton(vendingMachineIcon);
				machineButton.setRolloverIcon(vendingMachineIcon);
				machineButton.setBounds(foodJoint.getXPostion(), foodJoint.getYPostion(), 25, 25);
				final JLabel machineDetailsLabel=new JLabel(foodJoint.getName());
				final JLabel machineLocationLabel=new JLabel("Location:"+foodJoint.getLocation());
				machineDetailsLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-30,250,20);
				machineLocationLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-15,250,20);
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
						machineLocationLabel.setBackground(Color.CYAN);
						machineDetailsLabel.setBackground(Color.CYAN);
						machineLocationLabel.setOpaque(true);
						machineDetailsLabel.setOpaque(true);
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
						LoginCafe openLoginFrame=new LoginCafe();
						openLoginFrame.setVisible(true);
					}
					
				});
				
				panel.add(machineButton);
				
			}
			if(foodJoint.getIsVendingMachine()){
				Icon vendingMachineIcon = new ImageIcon("/Users/twinklesiva05/Desktop/vending_icon.gif");
				machineButton = new JButton(vendingMachineIcon);
				machineButton.setRolloverIcon(vendingMachineIcon);
				machineButton.setBounds(foodJoint.getXPostion(), foodJoint.getYPostion(), 25, 25);
				final JLabel machineDetailsLabel=new JLabel(" "+foodJoint.getName());
				final JLabel machineLocationLabel=new JLabel(" Location:"+foodJoint.getLocation());
				machineDetailsLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-30,250,20);
				machineLocationLabel.setBounds(foodJoint.getXPostion()+20,foodJoint.getYPostion()-15,250,20);
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
						machineLocationLabel.setBackground(Color.CYAN);
						machineDetailsLabel.setBackground(Color.CYAN);
						machineLocationLabel.setOpaque(true);
						machineDetailsLabel.setOpaque(true);
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
						JFrame openLoginFrame=new LoginVendingMachinePanel();
						openLoginFrame.setVisible(true);
					}
				});
				
				panel.add(machineButton);
			}
			setVisible(true);
		}
		
		add(panel);
		setVisible(true);
		setSize(700,700);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// /Users/akhilajanapareddy/Desktop/cups.jpg
	}
}
