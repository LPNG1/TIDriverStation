package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.sensors.SensorView;

public class OpenSensorViewAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("opening sensor view");
		SensorView.init();
	}

	
	
	
}
