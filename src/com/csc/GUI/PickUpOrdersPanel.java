package com.csc.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.csc.UserService;
import com.csc.model.FoodPurchaseTransaction;

/**
 *
 * @author twinklesiva05
 */
public class PickUpOrdersPanel extends javax.swing.JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form PickUpOrders
     */
	
	 
    public PickUpOrdersPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        
        
        jScrollPane1.setViewportView(jTable1);
        updateTableContent();

        jButton1.setText("PickUp");
      Timer timer=new Timer(2000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTableContent();
			}

		});
	
        timer.start();
        jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                for(int i=0;i<pickUpList.size();i++){
                    FoodPurchaseTransaction orderFoodPurchaseTransaction=pickUpList.get(i);
                    if(currentUserUnpickedOrders.pickUpOrder(orderFoodPurchaseTransaction)){
                        pickUpList.remove(i);
                        updateTableContent();
                    }
                    else{
                        //TODO Test this.
                        JOptionPane.showInputDialog(getRootPane(),
                                "Please try again",
                                "Inane warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        jButton2.setText("Refresh Table");
        
        
        jButton2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateTableContent();
			}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );
    }// </editor-fold>                        

    

    public void updateTableContent() {
        // TODO Auto-generated method stub
    	DefaultTableModel model=new DefaultTableModel(new Object[]{"check/uncheck", "OrderDetails","OrderLocation"},0){
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
    	
        jTable1.setModel(model);
        getOrderDetails(model);
        jTable1.getModel().addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
                // TODO Auto-generated method stub
                System.out.println("Table changed"+e.getFirstRow());
                getOrderItems(e.getFirstRow());
            }

            private void getOrderItems(int firstRow) {
                // TODO Auto-generated method stub
                if(jTable1.getValueAt(firstRow, 0).equals(true)){
                    Integer id=(Integer) jTable1.getValueAt(firstRow,1);
                    FoodPurchaseTransaction order=new FoodPurchaseTransaction();
                    order.setOrderDetails(id);
                    pickUpList.add(order);
                }
                else{
                    int id=(Integer)jTable1.getValueAt(firstRow, 1);
                    for(int i=0;i<pickUpList.size();i++){
                        FoodPurchaseTransaction orderToBeDeleted=pickUpList.get(i);
                        if(id==orderToBeDeleted.getId()){
                            pickUpList.remove(i);
                        }
                    }
                }
            }
        });
    }

    private void getOrderDetails(DefaultTableModel model) {
        // TODO Auto-generated method stub
    	
        ArrayList<FoodPurchaseTransaction> orders=currentUserUnpickedOrders.getCurrentUserUnpickedOrders();
       if(orders.size()>0){
    	   for(int i=0;i<orders.size();i++){
               FoodPurchaseTransaction order=orders.get(i);
               model.addRow(new Object[]{Boolean.FALSE,order.getId(),order.getFoodJoint().getLocation()});
           }
       }
    }

    // Variables declaration - do not modify  
    ArrayList<FoodPurchaseTransaction> pickUpList=new ArrayList<FoodPurchaseTransaction>();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private UserService currentUserUnpickedOrders=new UserService();
    // End of variables declaration                   
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		updateTableContent();
		
	}
}