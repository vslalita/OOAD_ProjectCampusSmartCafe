package com.csc.views;
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

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.model.FoodJoint;

public class MapFrame extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]){
		new MapFrame().start();
	}

	public void start(){
		final ImagePanel panel = new ImagePanel(new ImageIcon("/Users/twinklesiva05/Desktop/mapImage.png").getImage());
		
		CSCApplicationContext app=new CSCApplicationContext();
		ArrayList<FoodJoint> foodJoints=app.getAllFoodJoints();
		JButton machineButton;
		for(int i=0;i<foodJoints.size();i++){
			final FoodJoint foodJoint=foodJoints.get(i);
			if(foodJoint.isCafe()){
				Icon vendingMachineIcon = new ImageIcon("/Users/twinklesiva05/Desktop/icon_food.jpg");
				machineButton = new JButton(vendingMachineIcon);
				machineButton.setRolloverIcon(vendingMachineIcon);
				machineButton.setBounds(foodJoint.getXPosition(), foodJoint.getYPosition(), 25, 25);
				final JLabel machineDetailsLabel=new JLabel(foodJoint.getName());
				final JLabel machineLocationLabel=new JLabel("Location:"+foodJoint.getLocation());
				machineDetailsLabel.setBounds(foodJoint.getXPosition()+20,foodJoint.getXPosition()-30,250,20);
				machineLocationLabel.setBounds(foodJoint.getXPosition()+20,foodJoint.getXPosition()-15,250,20);
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
						CSCApplicationContext.getFoodJointController().selectFoodJoint(foodJoint);						
						System.out.println("Created Cafe");
						LoginCafe openLoginFrame=new LoginCafe();
						openLoginFrame.setVisible(true);
					}
					
				});
				
				panel.add(machineButton);
				
			}
			if(foodJoint.isVendingMachine()){
				Icon vendingMachineIcon = new ImageIcon("/Users/twinklesiva05/Desktop/vending_icon.gif");
				machineButton = new JButton(vendingMachineIcon);
				machineButton.setRolloverIcon(vendingMachineIcon);
				machineButton.setBounds(foodJoint.getXPosition(), foodJoint.getXPosition(), 25, 25);
				final JLabel machineDetailsLabel=new JLabel(" "+foodJoint.getName());
				final JLabel machineLocationLabel=new JLabel(" Location:"+foodJoint.getLocation());
				machineDetailsLabel.setBounds(foodJoint.getXPosition()+20,foodJoint.getXPosition()-30,250,20);
				machineLocationLabel.setBounds(foodJoint.getXPosition()+20,foodJoint.getXPosition()-15,250,20);
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

					};

				});
				
				machineButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						CSCApplicationContext.getFoodJointController().selectFoodJoint(foodJoint);
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
