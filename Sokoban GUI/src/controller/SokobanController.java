package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.commands.DisplayGUICommand;
import controller.commands.DisplayLevelCommand;
import controller.commands.ExitCommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveLevelCommand;
import controller.commands.SokobanCommand;
import model.IModel;
import view.IView;

public class SokobanController implements Observer{
	
	private IView ui;
	private IModel model;
	private Controller controller;
	HashMap<String, SokobanCommand> commandsCreator;
	
	public SokobanController(IView ui,IModel model) {
		
		this.ui=ui;
		this.model=model;
		this.controller=new Controller();
		
		this.commandsCreator= new HashMap<String,SokobanCommand>();
		this.commandsCreator.put("LOAD", new LoadLevelCommand());
		this.commandsCreator.put("DISPLAY", new DisplayLevelCommand());//separate to 2
		this.commandsCreator.put("MOVE", new MoveCommand());
		this.commandsCreator.put("SAVE", new SaveLevelCommand());
		this.commandsCreator.put("EXIT", new ExitCommand());
		this.commandsCreator.put("CHANGED", new DisplayGUICommand()); 
	}
	
	public IView getUi() {
		return ui;
	}

	public void setUi(IView ui) {
		this.ui = ui;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	private String[] objectToStringArray(Object obj){
		
		String [] result;
		String inputCommand=(String)obj;
		
		result=inputCommand.split(" ", 2);
		result[0]=result[0].toUpperCase();
		
		return result;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		String[]parmas=objectToStringArray(arg);
		SokobanCommand command=this.commandsCreator.get(parmas[0]);
		command.setParams(this, parmas[1]);
		try {
			this.controller.insertCommand(command);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
