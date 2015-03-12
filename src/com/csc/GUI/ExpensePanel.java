package com.csc.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.csc.CurrentSession;
import com.csc.model.FoodPreference;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author twinklesiva05
 */
public class ExpensePanel extends javax.swing.JPanel {

    private JLabel jLabel;
	/**
     * Creates new form PreferencesPanel
     */
    public ExpensePanel(JLabel jLabel) {
        initComponents();
        this.jLabel=jLabel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        RadioButtonsGroup = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("");

        jLabel1.setText("Enter Expenses:");

        RadioButtonsGroup.add(jRadioButton1);
        jRadioButton1.setText("Add");

        RadioButtonsGroup.add(jRadioButton2);
        jRadioButton2.setText("Reduce");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Save");
        jButton1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(jRadioButton2.isSelected()&&jRadioButton1.isSelected()){
					JOptionPane.showMessageDialog(null,"Cannot select both add and reduce");
					jTextField1.setText("");
					jTextField1.requestFocus();
				}
				else{
					if(jRadioButton2.isSelected()){
						try{
							int expenses=Integer.parseInt(jTextField1.getText());
							CurrentSession.getCurrentUser().updateExpenses(expenses,"reduce");
							jLabel.setText("Funds Remaining for this Month:"+CurrentSession.getCurrentUser().getRemainingExpenses());
						}catch(Exception NumberFormatException){
							JOptionPane.showMessageDialog(null,"Enter a valid number");
							jTextField1.setText("");
							jTextField1.requestFocus();

						}
					}
					else if(jRadioButton1.isSelected()){
						try{
							int expenses=Integer.parseInt(jTextField1.getText());
							CurrentSession.getCurrentUser().updateExpenses(expenses,"add");
							jLabel.setText("Funds Remaining for this Month:"+CurrentSession.getCurrentUser().getRemainingExpenses());
						}catch(Exception NumberFormatException){
							JOptionPane.showMessageDialog(null,"Enter a valid number");
							jTextField1.setText("");
							jTextField1.requestFocus();

						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Select one of add and reduce options");
						jTextField1.setText("");
						jTextField1.requestFocus();
					}

				}
			}

		});


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)))
                .addContainerGap(167, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(96, 96, 96))
        );
    }// </editor-fold>                        

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             


    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup RadioButtonsGroup;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}
