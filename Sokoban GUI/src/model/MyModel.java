package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;

import commons.Level;
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

		Thread t= new Thread(new Runnable() {	
			@Override
			public void run() {
				
				LevelLoader levelLoader= LevelLoaderFactory.create(filepath.substring(filepath.length()-3).toLowerCase());
				
				if (levelLoader==null){//extension not valid
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}
					
				try {
					setTheLevel(levelLoader.loadLevel(new FileInputStream(new File(filepath))));
					setChanged();
					notifyObservers("changed");
					
				} catch (ClassNotFoundException | IOException e) {//file not found
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}
			}
		});
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			setChanged();
			notifyObservers("Error "+e.getMessage());
		}
		
	}

	@Override
	public void SaveLevel(String filepath) {
		
		if(this.theLevel==null){
			setChanged();
			notifyObservers("Error You need to load level first!");
			return;
		}
		
		Thread t =new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				LevelSaver levelSaver=LevelSaverFactory.create(filepath.substring(filepath.length()-3).toLowerCase());
				if(levelSaver==null){//extension not valid
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}
				try {
					levelSaver.saveLevel(getTheLevel(), new FileOutputStream(new File(filepath)));
				} catch (IOException e) {
					setChanged();
					notifyObservers("Error Invalid file!");
					return;
				}				
			}
		});
		t.start();
	}

	@Override
	public void move(String moveInput) {
		
		if(this.theLevel==null){
			setChanged();
			notifyObservers("Error You need to load level first!");
			return;
		}

		ISokobanPolicy policy=new MySokobanPolicy(this.theLevel);
		IMoveType moveType=this.MoveTypeFactory.create(moveInput.toUpperCase());
		
		//moveType didn't created
		if (moveType==null){
			setChanged();
			notifyObservers("Error Invalid move type!");
			return;
		}
		
		policy.checkPolicy(this.theLevel.getListPlayer().get(0), moveType);
		setChanged();
		notifyObservers("changed");
	}

	@Override
	public int getSteps() {
		if(this.theLevel!=null)
			return this.theLevel.getSteps();
		return 0;
	}
}
