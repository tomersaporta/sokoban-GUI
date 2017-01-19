package controller.commands;

import java.util.HashMap;
/**
 *<h1>Command factory</h1> 
 *create command according to the string type
 */
public class CommandsFactory {
	
	private interface Creator{
		public ICommand create();
	}
	
	private class DisplayerCommandCreator implements Creator{
		@Override
		public ICommand create() {
			return new DisplayLevelCommand();
		}
	}
	
	private class ExitCommandCreator implements Creator{
		@Override
		public ICommand create() {
			return new ExitCommand();
		}
	}
	
	private class LoadLevelCommandCreator implements Creator{
		@Override
		public ICommand create() {
			return new LoadLevelCommand();
		}
	}
	
	private class SaveLevelCommandCreator implements Creator{
		@Override
		public ICommand create() {
			return new SaveLevelCommand();
		}
	}
	
	private class MoveCommandCreator implements Creator{
		@Override
		public ICommand create() {
			return new MoveCommand();
		}
	}
	
	HashMap<String, Creator> commandsCreator;
	
	public CommandsFactory() {
		this.commandsCreator= new HashMap<String,Creator>();
		this.commandsCreator.put("LOAD", new LoadLevelCommandCreator());
		this.commandsCreator.put("DISPLAY", new DisplayerCommandCreator());
		this.commandsCreator.put("MOVE", new MoveCommandCreator());
		this.commandsCreator.put("SAVE", new SaveLevelCommandCreator());
		this.commandsCreator.put("EXIT", new ExitCommandCreator());
		
	}
	/**
	 * create command according to the string type
	 * @param type This is the key in the HashMap who map between the string(key)and the suitable creator(value)
	 * @return new command 
	 */
	public ICommand create(String type){
		Creator c=commandsCreator.get(type);
		
		if (c!=null)return c.create();
		return null;
	}
	
	
}
