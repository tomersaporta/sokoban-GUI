package controller.commands;

import java.io.IOException;

import controller.SokobanController;
import model.data.Level;
import view.displayer.Display2D;
import view.displayer.display;

public class DisplayLevelCommand implements SokobanCommand {

	display displayer;
	Level level;
	
	public DisplayLevelCommand() {
		this.displayer=null;
		this.level=null;
	}
	
	/**
	 * activate the suitable method in the Displayer
	 */
	@Override
	public void exceute() {
		try {
			this.displayer.displayLevel(level, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the parameters necessary for the receiver
	 * @param level display this level
	 */
	public void init (Level level){
		this.displayer= new Display2D();
		this.level= level;
		
		
	}

	@Override
	public void setParams(SokobanController sokoController, String input) {
		// TODO Auto-generated method stub
		
	}

}
