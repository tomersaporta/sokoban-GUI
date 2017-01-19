package controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import controller.commands.ICommand;

public class Controller {
	
	private BlockingQueue<ICommand> commandsQueue;
	private boolean stop;
	
	public Controller() {
		this.commandsQueue=new LinkedBlockingQueue<ICommand>();
		this.stop=false;
	}
	
	public BlockingQueue<ICommand> getCommandsQueue() {
		return commandsQueue;
	}

	public void setCommandsQueue(BlockingQueue<ICommand> commandsQueue) {
		this.commandsQueue = commandsQueue;
	}

	void insertCommand(ICommand command) throws InterruptedException{
		this.commandsQueue.put(command);
	}
	
	void start(){
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!stop){
					ICommand command;
					try {
						command = getCommandsQueue().take();
						command.exceute();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
	
	void stop(){
		this.stop=true;
	}
}

