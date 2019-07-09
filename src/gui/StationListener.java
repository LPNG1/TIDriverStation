package gui;

public class StationListener extends Thread{
	
	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {
			try{
				DriverStation.getInstance().setBatteryInfo();
			} catch(NullPointerException e) {
				
			}
		}
		
	}	
}