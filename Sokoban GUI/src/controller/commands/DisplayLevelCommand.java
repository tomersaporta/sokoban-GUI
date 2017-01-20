package controller.commands;


import java.io.IOException;

import model.IModel;
import view.IView;

public class DisplayLevelCommand extends Command {

	private IModel model;
	private IView view;
	
	public DisplayLevelCommand(IModel model, IView view) {
		this.model= model;
		this.view= view;
	}
	
	/**
	 * activate the suitable method in the Displayer
	 */
	@Override
	public void exceute() {
		try {
			this.view.displayLevel(this.model.getCurrentLevel(), System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
