package controller.commands;

import controller.SokobanController;
import model.IModel;

/**
 * <h1>LoadLevelCommand</h1>
 *activate the LoadLevel in the model
 */
public class LoadLevelCommand implements SokobanCommand {

	private String filepath;
	private IModel model;

	public LoadLevelCommand() {
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
	 * activate the suitable method in the model
	 */
	@Override
	public void exceute(){
		
		this.model.LoadLevel(this.filepath);
	}

	/**
	 * Initialize the parameters necessary for the exceute
	 */
	@Override
	public void setParams(SokobanController sokoController, String input) {
		setFilepath(input);
		setModel(sokoController.getModel());
	}


		
}
