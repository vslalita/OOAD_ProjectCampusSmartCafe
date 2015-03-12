package com.csc.GUI;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer{

	public CheckBoxCellRenderer() {
		setOpaque(false);
	}

	public Component getTableCellRendererComponent(
			JTable table, Object value,
			boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value != null) {
			this.setSelected( ( (Boolean) value).booleanValue());
		}
		else{
			this.setSelected (false);
		}
		setHorizontalAlignment (SwingConstants.CENTER); 
		return this;
	}

	
}