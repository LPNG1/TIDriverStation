package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import data.BatteryData;
import gui.actions.BrickConnectAction;
import gui.actions.RobotDisableAction;
import gui.actions.RobotEnableAction;
import gui.actions.ServerConnectAction;
import gui.actions.StartAutoAction;
import gui.actions.StopAutoAction;

/**
 * Driver station GUI Interface
 * TODO cleanup and make more user friend
 * @author John
 *
 */
public class DriverStation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2370347783906823570L;

	private Font defaultFont = new Font("Consolas", Font.BOLD, 20);
	
	private int PANEL_WIDTH = 900;
	private int PANEL_HEIGHT = 250;
	
	private int TEST_COLUMN = 20;
	private int CONNECT_COLUMN = 650;
	
	//components
		//menu
		private JMenuBar menuBar;
		private JMenu view;
		private JMenuItem sensors;
		
		//panel
		private JPanel p;
		
		//components
		private DSButton startAuto, stopAuto, enable, disable, connectToServer, connect;
		private DSLabel match, matchStatus, stage, matchStage, voltage, current, teamId, ip, status, number, connectionStatus;
		private DSTextField numberInput, ipInput;
		private JComboBox<String> teamList;
		
		private static DriverStation instance;
		
	public static void init() {
		if(instance ==null) {
			instance = new DriverStation();
		}
	}
	
	public static DriverStation getInstance() {
		if (instance == null) {
			init();
		}
		return instance;
	}
		
	private DriverStation() {
		
		//basic window info
		setSize(PANEL_WIDTH,PANEL_HEIGHT);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Treasure Island Driver Station");
		setResizable(false);
		
		//menu
		menuBar = new JMenuBar();
		view = new JMenu("View");
		sensors = new JMenuItem("Sensor Data");
		
		view.add(sensors);
		menuBar.add(view);
		setJMenuBar(menuBar);
		
		//create panel
		p = new JPanel();
		p.setBounds(0, 0, 900, 250);
		p.setBackground(Color.GRAY);
		p.setLayout(null);
		add(p);
		
		//start and stop auto
		startAuto = new DSButton("Start Auto", Color.GREEN);
		startAuto.setBounds(TEST_COLUMN, 20, 150, 40);
		startAuto.addActionListener(new StartAutoAction());
		startAuto.setEnabled(false);
		p.add(startAuto);
		
		stopAuto = new DSButton("Stop Auto", Color.RED);
		stopAuto.setBounds(TEST_COLUMN, 65, 150, 40);
		stopAuto.addActionListener(new StopAutoAction());
		stopAuto.setEnabled(false);
		p.add(stopAuto);
		
		//enable and disable
		enable = new DSButton("Enable", Color.GREEN);
		enable.setBounds(TEST_COLUMN, 120, 85, 40);
		enable.addActionListener(new RobotEnableAction());
		enable.setEnabled(false);
		p.add(enable);
		
		disable = new DSButton("Disable", Color.RED);
		disable.setBounds(TEST_COLUMN+90, 120, 85, 40);
		disable.addActionListener(new RobotDisableAction());
		disable.setEnabled(false);
		p.add(disable);
		
		//match info
		match = new DSLabel("Match: ");
		match.setBounds(200, 20, 80, 30);
		p.add(match);
		
		matchStatus = new DSLabel("Inactive");
		matchStatus.setBounds(280, 20, 100, 30);
		p.add(matchStatus);
		
		stage = new DSLabel("Stage: ");
		stage.setBounds(200, 40, 80, 30);
		p.add(stage);
		
		matchStage = new DSLabel("N/A");
		matchStage.setBounds(280, 40, 150, 30);
		p.add(matchStage);
		
		//connect to server
		connectToServer = new DSButton("Connect to Server", Color.GREEN);
		connectToServer.setBounds(200, 120, 200, 40);
		connectToServer.addActionListener(new ServerConnectAction());
		p.add(connectToServer);
		
		//battery icon
		BufferedImage bufferedBatIcon = null;
		Image scaledBatIcon = null;
		try {
			bufferedBatIcon = ImageIO.read(new File("./resources/batteryicon.png"));
			scaledBatIcon = bufferedBatIcon.getScaledInstance(100, 70, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Can't read file!");
			e.printStackTrace();
		}
		
		JLabel batIcon = new JLabel(new ImageIcon(scaledBatIcon));
		batIcon.setBounds(420, 10, 100, 100);
		p.add(batIcon);
		
		
		//battery info
		voltage = new DSLabel("0.00V");
		voltage.setBounds(530, 40, 200, 30);
		p.add(voltage);
		
		current = new DSLabel("0.00A");
		current.setBounds(530, 60, 200, 30);
		p.add(current);
		
		//team info
		teamId = new DSLabel("TEAM:");
		teamId.setBounds(420, 125, 75, 30);
		p.add(teamId);
		
		String[] teams = {"Red 1", "Red 2", "Blue 1", "Blue 2"};
		teamList = new JComboBox<String>(teams);
		teamList.setBounds(490, 125, 125, 30);
		teamList.setBackground(Color.DARK_GRAY);
		teamList.setFont(defaultFont);
		teamList.setForeground(Color.WHITE);
		teamList.setVisible(true);
		p.add(teamList);
		
		number = new DSLabel("#:");
		number.setBounds(625, 125, 30, 30);
		p.add(number);
		
		numberInput = new DSTextField();
		numberInput.setBounds(650, 125, 100, 30);
		p.add(numberInput);
		
		//brick info
		ip = new DSLabel("IP:");
		ip.setBounds(CONNECT_COLUMN, 20, 50, 30);
		p.add(ip);
		
		ipInput = new DSTextField();
		ipInput.setBounds(CONNECT_COLUMN+40, 20, 150, 30);
		p.add(ipInput);
		
		connect = new DSButton("Connect", Color.GREEN);
		connect.setBounds(CONNECT_COLUMN+40, 55, 150, 40);
		connect.addActionListener(new BrickConnectAction());
		p.add(connect);
		
		status = new DSLabel("Status: ");
		status.setBounds(CONNECT_COLUMN, 100, 100, 30);
		p.add(status);
		
		connectionStatus = new DSLabel("Not Connected");
		connectionStatus.setBounds(CONNECT_COLUMN+80, 100, 150, 30);
		connectionStatus.setForeground(Color.RED);
		p.add(connectionStatus);
		
		setVisible(true);
	}
	
	public void setBatteryInfo(BatteryData b) {
		voltage.setText((new DecimalFormat("#.##").format(b.getVoltage())) + "V");
		current.setText((new DecimalFormat("#.##").format(b.getCurrent())) + "A");
	}
	
	public void setMatchInfo() {
		//TODO implement and require data structure
	}
	
	public void setConnectionStatus(String newStatus, Color newColor) {
		connectionStatus.setText(newStatus);
		connectionStatus.setForeground(newColor);
	}
	
	public String getIP() {
		return ipInput.getText();
	}
	
	public void allowBrickConnection(boolean state) {
		connect.setEnabled(state);
	}
	
	public void allowRobotEnable(boolean state) {
		enable.setEnabled(state);
	}
	
	public void allowRobotDisable(boolean state) {
		disable.setEnabled(state);
	}
	
	public void allowStartAuto(boolean state) {
		startAuto.setEnabled(state);
	}
	
	public void allowStopAuto(boolean state) {
		stopAuto.setEnabled(state);
	}
	
}
