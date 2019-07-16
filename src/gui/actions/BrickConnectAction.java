package gui.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import communication.tcp.TCPCommunicator;
import communication.udp.UDPCommunicator;
import data.Constants;
import gui.DriverStation;
import gui.StationListener;

/**
 * Connects to the named brick from the driver station
 * @author John
 *
 */
public class BrickConnectAction implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//update info
		System.out.println("Connecting to robot");
		DriverStation.getInstance().setConnectionStatus("Connecting", Color.YELLOW);
		DriverStation.getInstance().allowBrickConnection(false);
		
		//try to connect
		try {
			TCPCommunicator.init(DriverStation.getInstance().getIP(), Constants.sendPort, Constants.recPort);
		} catch (Exception e) {
			//if cant connect - update info and return
			JOptionPane.showMessageDialog(null, "Cant connect to the robot! \n Check your PAN is connected and IP is correct!");
			if(e instanceof IOException) {
				System.out.println("Can't connect to robot!");
			} else if (e instanceof UnknownHostException) {
				System.out.println("Bad IP!");
			}
			e.printStackTrace();
			
			DriverStation.getInstance().setConnectionStatus("Not Connected", Color.RED);
			DriverStation.getInstance().allowBrickConnection(true);
			return;
		}
		
		UDPCommunicator.init();
		
		DriverStation.initSenderThread();
		DriverStation.initReaderThread();
		
		StationListener s = new StationListener();
		s.start();
		
		DriverStation.getInstance().allowRobotEnable(true);
		DriverStation.getInstance().allowTestAuto(true);
		DriverStation.getInstance().allowTestTeleop(true);
		DriverStation.getInstance().setConnectionStatus("Connected", Color.GREEN);
		
	}

}
