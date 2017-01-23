package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;

public class SokobanClientHandler extends Observable implements ClientHandler {

	private PrintWriter outClient;
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		
		this.outClient=new PrintWriter(outToClient);
	
		try {
			//Adapter from InputStream to BufferReader
			BufferedReader clientInput=new BufferedReader(new InputStreamReader(inFromClient));
			
			//open a new thread who reading from the client
			Thread fromClient=aSyncReadInputs(clientInput, "exit");
			
			fromClient.join();
			clientInput.close();
			this.outClient.close();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void readInputs(BufferedReader in, String exitStr){
		String line;
		boolean flag=false;
		
		try {
			while(!flag){
				 line=in.readLine();
				 if(line.equals(exitStr)){
					flag=true;
				}
			
				setChanged();
				notifyObservers(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Thread aSyncReadInputs(BufferedReader in, String exitStr){
		
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				readInputs(in, exitStr);
			}
		});
		t.start();
		return t;
		
	}

	private void sendOutput(char[][] levelBored){
		
		for(int i=0;i<levelBored.length;i++){
			
			this.outClient.println(levelBored[i]);
			this.outClient.flush();
			
		}
		
	}
	
	public void aSyncSendOutput(char[][] levelBored){
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				sendOutput(levelBored);
			}
		});
		t.start();
	}
	
}
