package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class DSTextField extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8058767204096940138L;

	private Font defaultFont = new Font("Consolas", Font.BOLD, 20);
	
	public DSTextField() {
		super();
		setBackground(Color.DARK_GRAY);
		setFont(defaultFont);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		setCaretColor(Color.WHITE);
		setVisible(true);
	}
	
}
