package boot;

import controller.server.MyServer;
import controller.server.SokobanClientHandler;

public class Run {

	public static void main(String[]args){
		
		SokobanClientHandler ch=new SokobanClientHandler();
		
		int port=2057;
		MyServer theServer=new MyServer(port, ch);
		theServer.start();
	}
}
	
