package gui.sensors;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.DSLabel;
import gui.StationListener;

public class SensorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7241300993496922786L;

	private int PANEL_WIDTH = 450;
	private int PANEL_HEIGHT = 450;
	
	//panel
	private JPanel p;
	
	private DSSensorInfo port1, port2, port3, port4;
	private DSMotorInfo portA, portB, portC, portD;
	private DSLabel sensorLabel, motorLabel;
	
	private static SensorListener listener;
	private static SensorView instance;
	
	
	

	public static void init() {
		if (instance == null) {
			instance = new SensorView();
		}
	}

	public static SensorView getInstance() {
		return instance;
	}

	public SensorView() {

		// basic window info
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Sensor Viewer");
		setResizable(false);
		
		//create panel
		p = new JPanel();
		p.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		p.setBackground(new Color(40, 40, 40));
		p.setLayout(null);
		add(p);
		
		sensorLabel = new DSLabel("Sensors");
		sensorLabel.setBounds(75, 20, 100, 20);
		p.add(sensorLabel);
		
		motorLabel = new DSLabel("Motors");
		motorLabel.setBounds(275, 20, 100, 20);
		p.add(motorLabel);
		
		port1 = new DSSensorInfo(20, 50, 1);
		p.add(port1);
		
		port2 = new DSSensorInfo(20, 130, 2);
		p.add(port2);
		
		port3 = new DSSensorInfo(20, 210, 3);
		p.add(port3);
		
		port4 = new DSSensorInfo(20, 290, 4);
		p.add(port4);
		
		portA = new DSMotorInfo(250, 50, "A");
		p.add(portA);
		
		portB = new DSMotorInfo(250, 130, "B");
		p.add(portB);
		
		portC = new DSMotorInfo(250, 210, "C");
		p.add(portC);
		
		portD = new DSMotorInfo(250, 290, "D");
		p.add(portD);
		
		listener = new SensorListener();
		listener.start();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.interrupt();
				while(listener.isAlive());
				instance = null;
			}
		});
		

		setVisible(true);
	}

	public DSSensorInfo getPort1() {
		return port1;
	}
	
	public DSSensorInfo getPort2() {
		return port2;
	}
	
	public DSSensorInfo getPort3() {
		return port3;
	}
	
	public DSSensorInfo getPort4() {
		return port4;
	}
	
	public DSMotorInfo getPortA() {
		return portA;
	}
	
	public DSMotorInfo getPortB() {
		return portB;
	}
	
	public DSMotorInfo getPortC() {
		return portC;
	}
	
	public DSMotorInfo getPortD() {
		return portD;
	}
	
	
}
