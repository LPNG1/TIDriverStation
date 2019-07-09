package gui.sensors;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SensorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7241300993496922786L;

	private int PANEL_WIDTH = 800;
	private int PANEL_HEIGHT = 800;
	
	//panel
	private JPanel p;
	
	private static SensorView instance;
	
	

	public static void init() {
		if (instance == null) {
			instance = new SensorView();
		}
	}

	public static SensorView getInstance() {
		if (instance == null) {
			init();
		}
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
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				instance = null;
			}
		});
		

		setVisible(true);
	}

}
