package controller.commands;

import java.io.IOException;

import controller.SokobanController;
/**
 *<h1>Exit Level Command</h1> 
 *close all the files, release resources
 */
public class ExitCommand implements SokobanCommand {

	public ExitCommand() {
		
	}
	
	
	/**
	 * close all the files, release resources
	 */
	@Override
	public void exceute() {
		
		
		System.out.close();
		try {
			System.in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void setParams(SokobanController sokoController, String input) {
		// TODO Auto-generated method stub
		
	}
	


	
}
