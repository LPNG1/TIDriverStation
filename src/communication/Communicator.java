package communication;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Communicator {

	private static Socket s;
	private static PrintStream ps;
	private static Scanner reader;
	
	private static JSONParser parser = new JSONParser();
	
	/**
	 * Connect to the robot and create components
	 * @param ip robot's ip
	 * @param port port to open connection on
	 */
	public static void init(String ip, int port) {
		connectToRobot(ip, port);
		createPrintStream();
		createScanner();
	}
	
	/**
	 * Connects to a robot
	 * @param ip
	 * @param port
	 */
	private static void connectToRobot(String ip, int port) {
		try {
			s = new Socket(ip, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a print stream that sends messages
	 */
	public static void createPrintStream() {
		if(s.isConnected()) {
			try {
				ps = new PrintStream(s.getOutputStream());
			} catch (IOException e) {
				System.out.println("Exception while creating print stream:");
				e.printStackTrace();
				return;
			}
		} else {
			System.out.println("Cannot create print stream - not connected");
		}
	}
	
	/**
	 * Creates a scanner that reads messages
	 */
	public static void createScanner() {
		if(s.isConnected()) {
			try {
				reader = new Scanner(s.getInputStream());
			} catch (IOException e) {
				System.out.println("Exception while creating scanner:");
				e.printStackTrace();
			}
		} else {
			System.out.println("Cannot create scanner - not connected");
		}
	}
	
	/**
	 * Sends a JSON message to the robot
	 * @param msg
	 */
	public static void sendMessage(JSONArray msg) {
		ps.println(msg);
	}
	
	/**
	 * Returns the next message in the queue
	 * @return
	 */
	public static JSONArray getNextMessage() {
		if(reader.hasNextLine()) {
			try {
				return (JSONArray) parser.parse(reader.nextLine());
			} catch (ParseException e) {
				System.out.println("Invalid message!");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Determines weather there is a message in the queue
	 * @return
	 */
	public static boolean hasNextMessage() {
		return reader.hasNext();
	}
}
