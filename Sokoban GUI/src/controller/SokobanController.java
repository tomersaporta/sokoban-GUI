package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.commands.DisplayLevelCommand;
import controller.commands.ExitCommand;
import controller.commands.ICommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveLevelCommand;
import model.IModel;
import view.IView;

public class SokobanController implements Observer{
	
	private IView ui;
	private IModel model;
	private Controller controller;
	HashMap<String, ICommand> commandsCreator;
	
	public SokobanController(IView ui,IModel model) {
		
		this.ui=ui;
		this.model=model;
		this.controller=new Controller();
		
		initcommandsCreator();
		
		this.controller.start();
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

	private void initcommandsCreator(){
		this.commandsCreator= new HashMap<String,ICommand>();
		this.commandsCreator.put("LOAD", new LoadLevelCommand(this.model));
		this.commandsCreator.put("DISPLAY", new DisplayLevelCommand(this.model,this.ui));//separate to 2
		this.commandsCreator.put("MOVE", new MoveCommand(this.model));
		this.commandsCreator.put("SAVE", new SaveLevelCommand(this.model));
		this.commandsCreator.put("EXIT", new ExitCommand());
		//this.commandsCreator.put("CHANGED", new DisplayGUICommand()); 
		this.commandsCreator.put("CHANGED", new DisplayLevelCommand(this.model,this.ui));
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
		
		String[]params=objectToStringArray(arg);
		ICommand command=this.commandsCreator.get(params[0]);
		if(params.length>1)
			command.setParams(params[1]);
		else
			command.setParams(null);
		try {
			this.controller.insertCommand(command);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
