package gui.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import communication.tcp.TCPCommunicator;
import communication.udp.UDPCommunicator;
import communication.udp.UDPReaderThread;
import communication.udp.UDPSenderThread;
import data.Constants;
import gui.DriverStation;

/**
 * Connects to the named brick from the driver station
 * @author John
 *
 */
public class BrickConnectAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//update info
		System.out.println("Connecting to brick");
		DriverStation.getInstance().setConnectionStatus("Connecting", Color.YELLOW);
		DriverStation.getInstance().allowBrickConnection(false);
		
		//try to connect
		try {
			TCPCommunicator.init(DriverStation.getInstance().getIP(), Constants.sendPort, Constants.recPort);
		} catch (Exception e) {
			//if cant connect - update info and return
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
		
		UDPSenderThread send = new UDPSenderThread(50);
		UDPReaderThread read = new UDPReaderThread();
		
		send.start();
		read.start();
		
		DriverStation.getInstance().allowRobotEnable(true);
		DriverStation.getInstance().allowStartAuto(true);
		DriverStation.getInstance().allowStopAuto(true);
		DriverStation.getInstance().setConnectionStatus("Connected", Color.GREEN);
		
	}

}
