package view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Scanner;

import commons.Level;
import model.data.SokoElements.Box;
import model.data.SokoElements.Player;
import model.data.SokoElements.movable;

public class CLIView extends Observable implements IView{
	private boolean close;

	public void StartMenu()
	{
		System.out.println("*************************************************");
		System.out.println("******************** SOKOBAN ********************");
		System.out.println("*************************************************");
		System.out.println("Please choose command to run:");
		System.out.println("> Load 'filename'\n> Display\n> Move {up,down,left,right}\n> Save 'filename'\n> Exit\n");
		System.out.println("*************************************************");
	}
	
	public void start() 
	{		
		//Menu
		StartMenu();
		Scanner scanner = new Scanner(System.in);
		
		Thread t = new Thread(new Runnable() 
		{			
			@Override
			public void run() 
			{
				while(true)
				{
					String command = scanner.nextLine();
					
					setChanged();
					notifyObservers(command);
					
					if(command.equals("exit"))
						break;
				}
			}
		});
			
		t.start();
	}
	
	

	public boolean isClose()
	{
		return close;
	}

	public void setClose(boolean close)
	{
		this.close = close;
	}


	@Override
	public void displayLevel(Level myLevel, OutputStream output) throws IOException {
		
	BufferedWriter out = new BufferedWriter(new OutputStreamWriter(output));
		
		//Scanning the two arrays of level and prints to representer of each sokobanEntity
		for(int i=0;i<myLevel.getRow() ;i++){
			for (int j=0;j<myLevel.getCol();j++){
				movable m = myLevel.getMovearr()[i][j];
				if(m instanceof Player || m instanceof Box)
					out.write(m.getType());
				else
					out.write(myLevel.getBackground()[i][j].getType());
				
			}
			if (i == myLevel.getRow() - 1)
			{
				break;
			}
				
			out.newLine();
		}
		
		//out.close();
		out.newLine();
		out.flush();
	}

		

}