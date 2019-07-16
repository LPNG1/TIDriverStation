package gui.sensors;

import java.awt.Color;

import javax.swing.JPanel;

import data.MotorData;
import gui.DSLabel;

public class DSMotorInfo extends JPanel{
	
	private DSLabel portLabel, portName, typeLabel, typeInput, valueLabel, valueInput;

	public DSMotorInfo(int x, int y, String port) {
		setBounds(x, y, 200, 75);
		setBackground(new Color(40, 40, 40));
		setLayout(null);
		
		portLabel = new DSLabel("Port: ");
		portLabel.setBounds(0, 0, 70, 20);
		add(portLabel);
		
		portName = new DSLabel(port);
		portName.setBounds(80,0, 20, 20);
		add(portName);
		
		typeLabel = new DSLabel("Type: ");
		typeLabel.setBounds(0, 0, 80, 60);
		add(typeLabel);
		
		typeInput = new DSLabel("Null");
		typeInput.setBounds(80,0, 150, 60);
		add(typeInput);
		
		valueLabel = new DSLabel("Value: ");
		valueLabel.setBounds(0, 0, 80, 100);
		add(valueLabel);
		
		valueInput = new DSLabel("0");
		valueInput.setBounds(80,0, 150, 100);
		add(valueInput);
		
		setVisible(true);
	}
	
	public void updateMotorInfo(MotorData m) {
		typeInput.setText(m.getType());
		valueInput.setText(Long.toString(m.getEncoderValue()));
	}
	
}
