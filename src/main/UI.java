package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import config.UIConfig;
import config.UiTable;

public class UI extends UIConfig implements MouseListener,ActionListener{
	
		public static void main(String[] args) {
			try {
				UI ui = new UI("Net Shell");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		JTable tb,dataTable;
		DefaultTableModel dataModel;
		BackProcess back;
		int loadStatus = 0;
		
		public UI(String title) throws Exception {
			super(title);
			
			this.getContentPane().add(this.mainPage(this.WIDTH,this.getHeight(),new String[][] {}));
			
		    this.revalidate();
		}
		
		private JPanel mainPage(int  w , int h,String[][] detailsTable) {
			
			JButton close = new JButton("X");
			close.setOpaque(false);
			close.setContentAreaFilled(false);
			close.setBorderPainted(false);
			close.setForeground(Color.white);
			close.setBounds(WIDTH-50,20, 50, 20);
			close.addActionListener(this);
			close.setFont(new Font("Calibri", Font.BOLD, 20));
			getContentPane().add(close);
			
			JLabel logo = new JLabel("Network Shell");
			logo.setForeground(Color.white);
			logo.setBounds(20,20, 180, 20);
			logo.setFont(new Font("Calibri", Font.BOLD, 20));
			getContentPane().add(logo);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBounds(0,0,w,h);
			panel.setBackground(new Color(45,45,45));
			
			panel.add(makeTable());
			panel.add(dataTable(detailsTable));
			
			return panel;
		}
		
		private JScrollPane makeTable(){
			JScrollPane scroll = null;
			try {
				String data[][] = new BackProcess().getWifiNameArray();
				tb = new UiTable(data,new String[] {"Wifi"});
				tb.addMouseListener(this);
				scroll = new JScrollPane(tb);
				scroll.setBounds(30, 70,250, 250);
				scroll.setBorder(new EmptyBorder(0,0,0,0));
				scroll.getViewport().setBackground(new Color(44,51,57));
				return scroll;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return scroll;
		}

		private JScrollPane dataTable(String[][] data) {
			JScrollPane scroll = null;
			try {
				dataModel = new DefaultTableModel(data,new String[] { "Data","" });
				dataTable = new UiTable(dataModel);
				scroll = new JScrollPane(dataTable);
				scroll.setBounds(300, 70,250, 250);
				scroll.setBorder(new EmptyBorder(0,0,0,0));
				scroll.getViewport().setBackground(new Color(44,51,57));

				return scroll;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return scroll;
		}
		
		@Override
		public void mouseClicked(MouseEvent event) {
			if(tb.getSelectedRow()>-1) {
				try {
					String[][] detailsTable = new BackProcess().getWifiDataByNameArray(tb.getValueAt(tb.getSelectedRow(), 0).toString());
					if (dataModel.getRowCount() > 0) {
					    for (int i = dataModel.getRowCount() - 1; i > -1; i--) {
					    	dataModel.removeRow(i);
					    }
					}
				    for (int i = 0; i <detailsTable.length; i++) {dataModel.insertRow(i,detailsTable[i]);}
					dataModel.fireTableDataChanged();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("X")) {
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
}
