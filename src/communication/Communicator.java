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
	
	public static void init(String ip, int port) {
		connectToRobot(ip, port);
		createPrintStream();
		createScanner();
	}
	
	public static void connectToRobot(String ip, int port) {
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
	
	public static void sendMessage(JSONArray msg) {
		ps.println(msg);
	}
	
	public static JSONArray getNextMsg() {
		try {
			return (JSONArray) parser.parse(reader.nextLine());
		} catch (ParseException e) {
			System.out.println("Invalid message!");
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean hasNextMessage() {
		return reader.hasNext();
	}
}
