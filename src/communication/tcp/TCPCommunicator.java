package communication.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * TCP Communication utilities
 * @author John
 *
 */
public class TCPCommunicator {

	private static Socket TCPsendSocket;
	private static Socket TCPrecSocket;
	private static PrintStream ps;
	private static Scanner reader;

	private static JSONParser parser = new JSONParser();
	
	/**
	 * Connect to the robot and create components
	 * @param ip robot's ip
	 * @param port port to open connection on
	 */
	public static void init(String ip, int sendPort, int recPort) {
		connectToRobot(ip, sendPort, recPort);
		createPrintStream();
		createScanner();
	}
	
	
	/**
	 * Connects to a robot using TCP sockets
	 * @param ip
	 * @param sendPort
	 * @param recPort
	 */
	private static void connectToRobot(String ip, int sendPort, int recPort) {
		try {
			TCPsendSocket = new Socket(ip, sendPort);
			TCPrecSocket = new Socket(ip, recPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a print stream that sends messages over the send socket
	 */
	private static void createPrintStream() {
		if(TCPsendSocket.isConnected()) {
			try {
				ps = new PrintStream(TCPsendSocket.getOutputStream());
			} catch (IOException e) {
				System.out.println("Exception while creating print stream:");
				e.printStackTrace();
			}
		} else {
			System.out.println("Cannot create print stream - not connected");
		}
	}
	
	/**
	 * Creates a scanner that reads messages from the receive socket
	 */
	private static void createScanner() {
		if(TCPrecSocket.isConnected()) {
			try {
				reader = new Scanner(TCPrecSocket.getInputStream());
			} catch (IOException e) {
				System.out.println("Exception while creating scanner:");
				e.printStackTrace();
			}
		} else {
			System.out.println("Cannot create scanner - not connected");
		}
	}
	
	/**
	 * Sends a JSON message to the robot via the printstream
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
	 * Determines whether there is a message in the queue
	 * @return
	 */
	public static boolean hasNextMessage() {
		return reader.hasNext();
	}
	
	/**
	 * Gets the connection's target IP
	 * @return
	 */
	public static InetAddress getIP() {
		return TCPsendSocket.getInetAddress();
	}
	
}
