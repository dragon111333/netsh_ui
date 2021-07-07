package config;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UiTable  extends JTable{
		public UiTable() {
			
		}
		
		public UiTable(String[][] data,String[] head) {
			super(data,head);
			getConfig();
		}
		
		public UiTable(DefaultTableModel model) {
			this.setModel(model);
			getConfig();
		}
		
		public void getConfig() {
			this.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
			this.setFont(new Font("Calibri", Font.BOLD, 12));
			this.getTableHeader().setOpaque(false);
			this.getTableHeader().setBackground(Color.black);
			this.getTableHeader().setForeground(Color.white);
	        this.getTableHeader().setReorderingAllowed(false);
			this.setRowHeight(25);
	        this.setFocusable(false);
	        this.setIntercellSpacing(new java.awt.Dimension(0, 0));
	        this.setRowHeight(25);
	        this.setSelectionBackground(new java.awt.Color(232, 57, 95));
	        this.setShowVerticalLines(false);
		}
}
