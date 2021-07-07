package config;

import java.awt.Color;

import javax.swing.JFrame;

public class UIConfig extends JFrame{
	public final  int HIGHT = 400;
		public final int WIDTH =600;
		
		public UIConfig(String title) {
			super(title);
		    this.setUndecorated(true);
			this.setLocation(500,200);
			this.setSize(WIDTH,HIGHT);
			this.getContentPane().setBackground(new Color(45,45,45));
			this.getContentPane().setLayout(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}
}
