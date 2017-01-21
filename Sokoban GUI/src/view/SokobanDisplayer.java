package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SokobanDisplayer extends Canvas{
	
	char[][] levelData;
	
	private StringProperty wallFileName;
	private StringProperty playerFileName;
	private StringProperty boxFileName;
	private StringProperty targetFileName;
	private StringProperty backgrounFileName;
	
	
	public void setLevelData(char[][] levelData) {
		this.levelData = levelData;
		redraw();
	}
	
	public SokobanDisplayer() {
		this.wallFileName= new SimpleStringProperty();
		this.playerFileName= new SimpleStringProperty();
		this.boxFileName= new SimpleStringProperty();
		this.backgrounFileName= new SimpleStringProperty();
		this.targetFileName= new SimpleStringProperty();
	}
	
	public String getWallFileName() {
		return wallFileName.get();
	}


	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}


	public String getPlayerFileName() {
		return playerFileName.get();
	}


	public void setPlayerFileName(String playerFileName) {
		this.playerFileName.set(playerFileName); ;
	}


	public String getBoxFileName() {
		return boxFileName.get();
	}


	public void setBoxFileName(String boxFileName) {
		this.boxFileName.set(boxFileName);;
	}


	public String getTargetFileName() {
		return targetFileName.get();
	}


	public void setTargetFileName(String targetFileName) {
		this.targetFileName.set(targetFileName);;
	}


	public String getBackgrounFileName() {
		return backgrounFileName.get();
	}

	
	public void setBackgrounFileName(String backgrounFileName) {
		this.backgrounFileName.set(backgrounFileName);;
	}


	public void redraw(){
		
		if(this.levelData!=null){
			//the canvas sizes
			double W= getWidth();
			double H= getHeight();
			
			//our items size- all the cells will be in the same size
			double w= W/levelData[0].length;
			double h=H/levelData.length;
			
			GraphicsContext gc =getGraphicsContext2D();
			
			gc.clearRect(0, 0, W, H);
			
			Image wall = null;
			Image box = null;
			Image player = null;
			Image background = null;
			Image target = null;
			
			try {
				wall= new Image(new FileInputStream(wallFileName.get()));
				box= new Image(new FileInputStream(boxFileName.get()));
				player= new Image(new FileInputStream(playerFileName.get()));
				background= new Image(new FileInputStream(backgrounFileName.get()));
				target= new Image(new FileInputStream(targetFileName.get()));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<levelData.length;i++)
				for (int j=0;j<levelData[0].length;j++){
					switch (levelData[i][j]){
					
					case '#':
						gc.drawImage(wall, j*w, i*h, w, h);
						break;
						
					case '@':
						gc.drawImage(box, j*w, i*h, w, h);
						break;
						
					case 'A':
						gc.drawImage(player, j*w, i*h, w, h);
						break;
						
					case ' ':
						gc.drawImage(background, j*w, i*h, w, h);
						break;
					
					case 'o':
						gc.drawImage(target, j*w, i*h, w, h);
						break;
						
					default:
						break;
					}
				}
			
		}
		
	}
	
}
