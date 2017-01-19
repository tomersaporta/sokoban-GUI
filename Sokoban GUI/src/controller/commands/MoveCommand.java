package controller.commands;

import controller.SokobanController;
import model.IModel;

/**
 * <h1>MoveCommand</h1>
 * activate the move in the model
 */

public class MoveCommand implements SokobanCommand {
	
	private String moveInput;
	private IModel model;
	
	public MoveCommand() {
		
		this.moveInput=null;
		this.model=null;
	}
	

	public String getMoveInput() {
		return moveInput;
	}


	public void setMoveInput(String moveInput) {
		this.moveInput = moveInput;
	}


	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}


	/**
	 * activate the move in the model
	 */
	@Override
	public void exceute() {
		this.model.move(this.moveInput);
	}
	
	/**
	 * Initialize the parameters necessary for the exceute
	 */
	@Override
	public void setParams(SokobanController sokoController, String input) {
		setModel(sokoController.getModel());
		setMoveInput(input);
	}

}
