package controller.commands;

import controller.server.SokobanClientHandler;
import model.IModel;

public class DisplayCLICommand extends Command {

	private SokobanClientHandler clientHandler;
	private IModel model;
	
	public DisplayCLICommand(IModel model, SokobanClientHandler clientHandler) {
		this.model=model;
		this.clientHandler=clientHandler;
	}
	
	@Override
	public void exceute() {
		this.clientHandler.sendLevel(this.model.getCurrentLevel().getLevelBored());
		
		
	}

}
