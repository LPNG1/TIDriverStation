package gui.sensors;

import java.awt.Color;

import javax.swing.JPanel;

import data.SensorData;
import gui.DSLabel;

public class DSSensorInfo extends JPanel{
	
	private int port;
	
	private DSLabel portLabel, portNum, typeLabel, typeInput, valueLabel, valueInput; 

	public DSSensorInfo(int x, int y, int port) {
		setBounds(x, y, 200, 75);
		setBackground(new Color(40, 40, 40));
		setLayout(null);
		
		portLabel = new DSLabel("Port: ");
		portLabel.setBounds(0, 0, 70, 20);
		add(portLabel);
		
		portNum = new DSLabel(Integer.toString(port));
		portNum.setBounds(80,0, 20, 20);
		add(portNum);
		
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
	
	public void updateSensorInfo(SensorData s) {
		typeInput.setText(s.getType());
		valueInput.setText(Double.toString(s.getValue()));
	}
	
	
}
