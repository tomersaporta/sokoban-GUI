package controller.commands;

import model.IModel;

/**
 * <h1>MoveCommand</h1>
 * activate the move in the model
 */

public class MoveCommand extends Command {
	
	private IModel model;
	
	public MoveCommand(IModel model) {
		this.model=model;
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
		this.model.move(getParams());
	}
	

}
