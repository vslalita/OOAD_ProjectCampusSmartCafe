/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.GUI;

import com.csc.*;
import com.csc.model.FoodItem;
import com.csc.model.FoodPreference;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author twinklesiva05
 */
public class HomPageGUI extends javax.swing.JFrame {

	/**
	 * Creates new form MainFrame
	 */
	public HomPageGUI() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	
	DefaultTableModel model;
	private void initComponents() {

		
		model = new DefaultTableModel(new Object[]{"check/uncheck","id", "Item Name", "Price", "Calories"}, 0) {
        
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex==0){
					return Boolean.class;
				}
				else{
					return super.getColumnClass(columnIndex);
				}
                
            }

        };
        
        populateItems();
       
        //populateItems();
		jRadioButton1 = new javax.swing.JRadioButton();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jButton2 = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable(model);
		jButton5 = new javax.swing.JButton();
		panel=new PickUpOrdersPanel();

		jRadioButton1.setText("jRadioButton1");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


		jLabel1.setText("Hello "+CurrentSession.getCurrentUser().getFirstName());
		
        jLabel1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout=(CardLayout)jPanel3.getLayout();
				layout.show(jPanel3, "UserInfo");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(30, 30, 30)
						.addComponent(jLabel1)
						.addContainerGap(194, Short.MAX_VALUE))
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap(21, Short.MAX_VALUE)
						.addComponent(jLabel1)
						.addContainerGap())
				);

		jButton1.setText("<html><center>Manage<br>Expenses</center></html>");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		
		
		
		
		jButton2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(items!=null&&items.size()>0){
					if(CurrentSession.getMachine().validateAndCreateTransaction(items)){
						FoodPreference fp=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
						jLabel6.setText("Daily Calories Remaining: "+fp.getRemainingCalories());
						jLabel7.setText("Funds remaining for this Month: "+CurrentSession.getCurrentUser().getRemainingExpenses()+"  ");
						JOptionPane.showMessageDialog(jPanel3,
								"Order Confirmed");
						jButton6.doClick();
						
					}
					else{
						JOptionPane.showMessageDialog(jPanel3,
								"You either exceeding your expenses or not meeting with your preferences. Please click on 'Manage Expenses' or 'Manage Preferences' to change",
								"Inane warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		
		FoodPreference fp=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
		CurrentSession.getCurrentUser().updateRemainingExpenses();
		fp.updateRemainingCalories();
		jLabel6.setText("Daily Calories Remaining: "+fp.getRemainingCalories());
		jLabel7.setText("Funds remaining for this Month: "+CurrentSession.getCurrentUser().getRemainingExpenses()+"  ");

		jButton3.setText("<html><center>View<br>Profile</center></html>");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout layout=(CardLayout)jPanel3.getLayout();
				layout.show(jPanel3, "EnterPreferences");
			}

		});
		


		//        FoodPreference fp=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
		//        jLabel6.setText("Daily Calories Remaining: "+fp.getRemainingCalories());
		//        jLabel7.setText("Funds remaining for this Month: "+CurrentSession.getCurrentUser().getRemainingExpenses()+"  ");

		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});


		jButton6.setText("<html><center>Pick Up<br>Orders</center></html>");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		
		JCheckBox checkBox=new JCheckBox();
		DefaultCellEditor editor=new DefaultCellEditor(checkBox);
		
		
//		jTable1.setModel(new javax.swing.table.DefaultTableModel(populateItems(jTable1)
//				,0));
		
//		jTable1.setModel(new javax.swing.table.DefaultTableModel(
//				populateItems(jTable1),
//				new String [] {
//					"check/uncheck","id", "Item Name", "Price", "Calories"
//				}
//				));
		jTable1.getColumnModel().getColumn(0).setCellEditor(editor);
		
		//jTable1.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxCellRenderer());

		jTable1.getModel().addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Table changed"+e.getFirstRow());
				getOrderItems(e.getFirstRow());
			}
		});
		

		
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
				jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addComponent(jLabel2)
						.addGap(18, 18, 18)
						.addComponent(jLabel7)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addContainerGap()
												.addComponent(jButton1))
												.addGroup(jPanel2Layout.createSequentialGroup()
														.addGap(15, 15, 15)
														.addComponent(jLabel6))
														.addGroup(jPanel2Layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(jButton3))
																.addGroup(jPanel2Layout.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(jButton4))
																		.addGroup(jPanel2Layout.createSequentialGroup()
																				.addContainerGap()
																				.addComponent(jButton6)).addGroup(jPanel2Layout.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(jButton5)))
																				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				)
				);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addGap(15, 15, 15)
						.addComponent(jLabel6)
						.addGap(12, 12, 12)
						.addComponent(jLabel7)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel2)
						.addGap(39, 39, 39)
						.addComponent(jButton1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton3)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jButton4)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButton6)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jButton5))
				);

		jPanel3.setLayout(new java.awt.CardLayout());
		jButton2.setText("Check Out");
		jLabel4.setText("Total Amount:0");
		jLabel5.setText("Total Calories:0");

		

		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 498, Short.MAX_VALUE)
				.addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(15, 15, 15)))
				);
		jPanel5Layout.setVerticalGroup(
				jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 285, Short.MAX_VALUE)
				.addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel4)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jLabel5)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
						.addComponent(jButton2)
						.addGap(34, 34, 34))
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel4Layout.createSequentialGroup()
										.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()))
				);
		jPanel4Layout.setVerticalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4)
								.addComponent(jLabel5))
								.addContainerGap())
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
										.addGap(0, 293, Short.MAX_VALUE)
										.addComponent(jButton2))
										.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(jPanel4Layout.createSequentialGroup()
														.addContainerGap()
														.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				);

		jPanel3.add(jPanel4, "Home");

		
		jPanel3.add("ViewProfile",new ProfileGUIPanel());
		jPanel3.add("EnterExpenses",new ExpensePanel(jLabel7));
		jPanel3.add("EnterPreferences",new PreferencePanel(jLabel6));
		jPanel3.add("PickUpOrders",panel);
		jPanel3.add("UserInfo",new UserInfoPanel());

		jButton4.setText("<html><center>Manage<br>Preferences</center></html>");

		jButton5.setText("<html><center>Go To<br>Home</center></html>");
	
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(18, 18, 18))
										.addGroup(layout.createSequentialGroup()
												.addContainerGap()
												.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createSequentialGroup()
																.addGap(38, 38, 38)
																.addComponent(jButton5))))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(36, 36, 36)
								.addComponent(jButton5)
								.addGap(64, 64, 64))
				);

		pack();
	}// </editor-fold>                        

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		// TODO add your handling code here:
		CardLayout layout=(CardLayout)jPanel3.getLayout();
		layout.show(jPanel3, "Home");
	}                                        

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		// TODO add your handling code here:
		CardLayout layout= (CardLayout)jPanel3.getLayout();
		layout.show(jPanel3, "PickUpOrders");
	}                                        


	ArrayList<FoodItem> items=new ArrayList<FoodItem>();
	protected void getOrderItems(int firstRow) {
		// TODO Auto-generated method stub
		FoodItem item;
		if(jTable1.getValueAt(firstRow, 0).equals(true)){
			Integer id=(Integer) jTable1.getValueAt(firstRow,1);
			String itemName=(String)jTable1.getValueAt(firstRow, 2);
			Integer itemPrice=(Integer)jTable1.getValueAt(firstRow, 3);
			Integer calories=(Integer)jTable1.getValueAt(firstRow, 4);
			item=new FoodItem(Integer.valueOf(id),itemName,Integer.valueOf(itemPrice),Integer.valueOf(calories));
			items.add(item);
			String totalAmount=jLabel4.getText();
			int amount=Integer.valueOf(totalAmount.substring(13))+itemPrice;
			jLabel4.setText("Total Amount:"+amount);
			String itemsTotalCalories=jLabel5.getText();
			int itemsTotalCaloriesValue=Integer.valueOf(itemsTotalCalories.substring(15))+calories;
			jLabel5.setText("Total Calories:"+itemsTotalCaloriesValue);
			System.out.println("Added item");
		}
		else{
			int id=(Integer)jTable1.getValueAt(firstRow, 1);
			for(int i=0;i<items.size();i++){
				FoodItem itemToBeDeleted=items.get(i);
				if(id==itemToBeDeleted.getId()){
					items.remove(i);
					String totalAmount=jLabel4.getText();
					int amount=Integer.valueOf(totalAmount.substring(13))-itemToBeDeleted.getPrice();
					jLabel4.setText("Total Amount:"+amount);
					String itemsTotalCalories=jLabel5.getText();
					int itemsTotalCaloriesValue=Integer.valueOf(itemsTotalCalories.substring(15))-itemToBeDeleted.getCalories();
					jLabel5.setText("Total Calories:"+itemsTotalCaloriesValue);
					System.out.println("Deleted item");
				}
			}
		}
	}

	private void populateItems() {
		// TODO remove instance and take from session
		ArrayList<FoodItem> items=CurrentSession.getMachine().displayItems();
		Object[][] objects=new Object[items.size()][5];
		int j=0;
		if(items!=null){
			for(int i=0;i<items.size();i++){
				FoodItem item=items.get(i);
//				objects[i][j]=Boolean.FALSE;
//				objects[i][j+1]=item.getId();
//				objects[i][j+2]=item.getItemName();
//				objects[i][j+3]=item.getPrice();
//				objects[i][j+4]=item.getCalories();
				model.addRow(new Object[]{Boolean.FALSE,item.getId(),item.getItemName(),item.getPrice(),item.getCalories()}); 
			}
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		// TODO add your handling code here:
		CardLayout layout =(CardLayout)jPanel3.getLayout();
		layout.show(jPanel3, "EnterExpenses");
	}                                        

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		// TODO add your handling code here:
		CardLayout layout =(CardLayout)jPanel3.getLayout();
		layout.show(jPanel3, "ViewProfile");
	}                          

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(HomPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(HomPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(HomPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(HomPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HomPageGUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify                     
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel panel;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	// End of variables declaration                   
}
