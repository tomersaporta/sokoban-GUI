package controller.commands;

import controller.SokobanController;
import model.IModel;

/**
 * <h1>Save Level Command<h1>
 * activate the SaveLevel in the model
 */
public class SaveLevelCommand implements SokobanCommand {

	private String filepath;
	private IModel model;
	
	public SaveLevelCommand() {
		this.filepath=null;
		this.model=null;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	/**
	 * activate the SaveLevel in the model
	 */
	@Override
	public void exceute() {
		this.model.SaveLevel(this.filepath);				
	}
	
	
	/**
	 * Initialize the parameters necessary for the excuete
	 */
	@Override
	public void setParams(SokobanController sokoController, String input) {
		setFilepath(input);
		setModel(sokoController.getModel());
	}
		
}
