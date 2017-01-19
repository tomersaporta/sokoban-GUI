package controller.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
	
	private int port;
	private ClientHandler ch;
	private volatile boolean stop;

	public MyServer(int port, ClientHandler ch) {
		this.port=port;
		this.ch=ch;
		stop=false;
	}
	
	private void runServer()throws Exception { 
		
		ServerSocket server=new ServerSocket(port);
		System.out.println("Server alive");
		server.setSoTimeout(10000); 
		
		while(!stop){//we want to wait to the next client- we handle the clients in a line
			try{
				Socket aClient=server.accept(); // blocking call
				System.out.println("The client is connected");
				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
				aClient.getInputStream().close(); 
				aClient.getOutputStream().close(); 
				aClient.close(); 
			}catch(SocketTimeoutException e) {continue;} 
		} 
		
		server.close();
	}
	
	public void start(){ 
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					runServer();
				}catch (Exception e){e.printStackTrace();}
			} 
		}).start();
	}

	public void stop(){ 
		stop=true; 
	}
	
	
}

