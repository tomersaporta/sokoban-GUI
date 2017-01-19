package controller.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.data.Level;
/**
 * <h1>The CLI</h1>
 * manage the whole commands
 */


public class CLI {
	
	ICommand command;
	Level level;
	Boolean flag;
	
	public CLI() {
		this.command=null;
		this.level= null;
		this.flag=true;
	}
	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public ICommand getCommand() {
		return command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	/**
	 * This is the main method who receive the command from the user and invoke the suitable command  
	 */
	public void run(){
		System.out.println("******************************");
		System.out.println("*****!Welcome to SOKOBAN!*****");
		System.out.println("******************************");
		System.out.println("please choose command:");
		System.out.println(">Load file name");
		System.out.println(">Display");
		System.out.println(">Move {up,down,left,right}");
		System.out.println(">Save file name");
		System.out.println(">Exit\n");
		
		//variable declaration
		Scanner in=new Scanner(System.in);
		String input;
		String []args;
		CommandsFactory factory=new CommandsFactory();
		
		
		do{
			//get the input from the user
			input=in.nextLine();
			
			//separate the input string
			args=input.split(" ", 2);
			args[0]=args[0].toUpperCase();
			
			//try{//commands cases
			switch(args[0]){
			case("LOAD"):
				if(args.length>1){//input validation
					this.command=factory.create(args[0]);
					LoadLevelCommand lc=(LoadLevelCommand)this.command;
					//lc.init(args[1], this);
					this.command.exceute();
					break;
				}
				else{
					System.out.println("Invalid Input, Try Again");
					continue;
				}

			case("SAVE"):
				if (args.length>1&&this.level!=null){//the level doesn't loaded, input validation
					this.command=factory.create(args[0]);
					SaveLevelCommand sc=(SaveLevelCommand)this.command;
					//sc.init(args[1], this);
					this.command.exceute();
					break;
				}
				else{
					System.out.println("Invalid Input, Try Again");
					continue;
				}
				
			case("DISPLAY"):
				if(this.level!=null){//the level doesn't loaded,input validation
					this.command=factory.create(args[0]);
					DisplayLevelCommand dc=(DisplayLevelCommand)this.command;
					dc.init(level);
					this.command.exceute();
					break;
				}
				else{
					System.out.println("Invalid Input, Try Again");
					continue;
				}
			case("MOVE"):
				if(args.length>1&&this.level!=null){//the level doesn't loaded, input validation
					this.command=factory.create(args[0]);
					MoveCommand mc=(MoveCommand)this.command;
					//mc.init(args[1], this);
					this.command.exceute();
					isEndOfLevel();
					break;
					
				}
				else{
					System.out.println("Invalid Input, Try Again");
					continue;
				}
			case("EXIT"):
				this.command=factory.create(args[0]);
				ExitCommand ec =(ExitCommand)this.command;
				//ec.init(this);
				this.command.exceute();
				break;
			default:
				System.out.println("Invalid Input, Try Again");
				continue;
			}
			
			/*} catch (FileNotFoundException e){
				System.out.println("Invalid Input:File Not Found, Try Again");
				continue;
				//e.printStackTrace();
				
			} catch (IOException e) {
				System.out.println("Invalid Input, Try Again");	
				continue;
				//e.printStackTrace();
			}*/
			
	
		}while(flag);
		
	}
	
	/**
	 * This method checks if the level ended, and print the information about the game round
	 */
	private void isEndOfLevel(){
		if (this.level.isEndOfLevel()){
			System.out.println("Congratulations- level completed!!!");
			System.out.println("Steps:"+((Level)this.level).getSteps());
			long endOfLevelTime=System.currentTimeMillis();
			long startofLevelTime = ((Level)this.level).getTime();
			System.out.println("Time:"+(endOfLevelTime-startofLevelTime)*0.001+"sec");
		}
			
	}

	
}

