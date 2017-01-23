package controller.commands;

import java.io.IOException;

import controller.Controller;

/**
 *<h1>Exit Level Command</h1> 
 *close all the files, release resources
 */
public class ExitCommand extends Command{

	private Controller controller;
	
	public ExitCommand(Controller controller) {
		this.controller= controller;
	}
	
	
	/**
	 * close all the files, release resources
	 */
	@Override
	public void exceute() {
		this.controller.stop();
		
	}
	
}
