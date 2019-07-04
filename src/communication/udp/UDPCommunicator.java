package communication.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * UDP Communication utiities
 * @author John
 *
 */
public class UDPCommunicator {
	
	private static DatagramSocket UDPSendSocket;
	private static DatagramSocket UDPRecSocket;
	
	private static InetAddress brickIP;
	private static int sendPort;
	private static int recPort;
	
	private static int bufferLength = 1024;
	
	private static JSONParser parser = new JSONParser();
	
	/**
	 * Initialize UDP connection
	 * @param ip stored IP used for sending data later
	 */
	public static void init(InetAddress ip) {
		//TODO: make this changeable
		sendPort = 4590;
		recPort = 4591;
		brickIP = ip;
		
		try {
			UDPSendSocket = new DatagramSocket();
			UDPRecSocket = new DatagramSocket(recPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Send a message to the connected robot via UDP socket
	 * @param msg
	 */
	public static void sendMessage(JSONArray msg) {
		byte[] messageBytes = msg.toString().getBytes();
		DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, brickIP, sendPort);
		try {
			UDPSendSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retreive a message from the UDP socket
	 * @return
	 */
	public static JSONArray getMessage() {
		
		//get a packet from the socket
		byte[] buffer = new byte[bufferLength];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try {
			UDPRecSocket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//parse the packet using JSON
		String msg = new String(packet.getData());
			//TODO: this but pretty and not hardcoded
		msg = msg.split("}}]")[0] + "}}]";
		
		JSONArray parsedMsg = null;
		try {
			parsedMsg = (JSONArray) parser.parse(msg);
		} catch (ParseException e) {
			System.out.println("Invalid Message");
			e.printStackTrace();
		}
		
		return parsedMsg;
	}
	
}
