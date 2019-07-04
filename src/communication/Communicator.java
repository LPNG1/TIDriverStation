package communication;

import communication.tcp.TCPCommunicator;
import communication.udp.UDPCommunicator;

/**
 * Communication wrapper for both TCP and UDP streams
 * @author John
 *
 */
public class Communicator {
	
	public static void initCommuniction(String ip) {
		
		//TODO not hardcode ports 
		TCPCommunicator.init(ip, 4590, 4591);
		UDPCommunicator.init(TCPCommunicator.getIP());
		
	}
	
	
}
