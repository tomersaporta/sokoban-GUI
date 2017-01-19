package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;

import model.data.Level;
import model.data.handle.LevelLoader;
import model.data.handle.LevelLoaderFactory;
import model.data.handle.LevelSaver;
import model.data.handle.LevelSaverFactory;
import model.policy.ISokobanPolicy;
import model.policy.MySokobanPolicy;
import model.policy.moveType.IMoveType;
import model.policy.moveType.MoveTypeFactory;

public class MyModel extends Observable implements IModel {
	
	Level theLevel;
	LevelLoaderFactory LevelLoaderFactory;
	LevelSaverFactory LevelSaverFactory;
	MoveTypeFactory MoveTypeFactory;
	
	public MyModel() {
		this.theLevel=null;
		this.LevelLoaderFactory = new LevelLoaderFactory();
		this.LevelSaverFactory=new LevelSaverFactory();
		this.MoveTypeFactory=new MoveTypeFactory();
		
	}
	
	public Level getTheLevel() {
		return theLevel;
	}

	public void setTheLevel(Level theLevel) {
		this.theLevel = theLevel;
	}


	@Override
	public Level getCurrentLevel() {
		return this.theLevel;
	}

	@Override
	public void LoadLevel(String filepath) {
		
		LevelLoader levelLoader= this.LevelLoaderFactory.create(filepath.substring(filepath.length()-3).toLowerCase());
		//if (levelLoader==null)
		//throw new IOException("Invalid Input, Try Again");
		
		Thread t= new Thread(new Runnable() {	
			@Override
			public void run() {
				try {
					setTheLevel(levelLoader.loadLevel(new FileInputStream(new File(filepath))));
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
		
		notifyObservers("changed");
	}

	@Override
	public void SaveLevel(String filepath) {
		
		LevelSaver levelSaver=this.LevelSaverFactory.create(filepath.substring(filepath.length()-3).toLowerCase());
		
		//saveLevel didn't created
		//if (levelSaver==null)
			//throw new IOException("Invalid command");
		Thread t =new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					levelSaver.saveLevel(getTheLevel(), new FileOutputStream(new File(filepath)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		t.start();
	}

	@Override
	public void move(String moveInput) {


		ISokobanPolicy policy=new MySokobanPolicy(this.theLevel);
		IMoveType moveType=this.MoveTypeFactory.create(moveInput.toUpperCase());
		
		//moveType didn't created
		//if (moveType==null)
			//throw new IOException("Invalid command");
		
		policy.checkPolicy(this.theLevel.getListPlayer().get(0), moveType);
		
		notifyObservers("changed");
	}
}
