package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class DSButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3774143559004825737L;

	private Font defaultFont = new Font("Consolas", Font.BOLD, 20);
	
	public DSButton(String text, Color color) {
		super(text);
		setBackground(Color.DARK_GRAY);
		setFont(defaultFont);
		setFocusPainted(false);
		setForeground(color);
		setBorder(null);
		setVisible(true);
	}
	
}
