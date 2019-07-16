package gui.sensors;

import gui.DriverStation;
import data.MotorData;
import data.SensorData;

public class SensorListener extends Thread {
	
	private MotorData[] motorArray;
	private SensorData[] sensorArray;
	
	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {			
			try {
				motorArray = DriverStation.getReader().getMotorData();
				sensorArray = DriverStation.getReader().getSensorData();
				
				for (int i = 0; i < motorArray.length; i++) {
					switch(motorArray[i].getPort()) {
					case "A":
						SensorView.getInstance().getPortA().updateMotorInfo(motorArray[i]);
						break;
					case "B":
						SensorView.getInstance().getPortB().updateMotorInfo(motorArray[i]);
						break;
					case "C":
						SensorView.getInstance().getPortC().updateMotorInfo(motorArray[i]);
						break;
					case "D":
						SensorView.getInstance().getPortD().updateMotorInfo(motorArray[i]);
						break;
					}
				}
				
				for (int i = 0; i < sensorArray.length; i++) {
					switch(sensorArray[i].getPort()) {
					case "1":
						SensorView.getInstance().getPort1().updateSensorInfo(sensorArray[i]);
						break;
					case "2":
						SensorView.getInstance().getPort2().updateSensorInfo(sensorArray[i]);
						break;
					case "3":
						SensorView.getInstance().getPort3().updateSensorInfo(sensorArray[i]);
						break;
					case "4":
						SensorView.getInstance().getPort4().updateSensorInfo(sensorArray[i]);
						break;
					}
				}
				
			} catch (NullPointerException e) {

			}
		}

	}
}
