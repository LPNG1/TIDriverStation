package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class DSLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7423541660441398935L;
	
	private Font defaultFont = new Font("Consolas", Font.BOLD, 20);
	
	public DSLabel(String text) {
		super(text);
		setFont(defaultFont);
		setForeground(Color.WHITE);
		setVisible(true);
	}

}
