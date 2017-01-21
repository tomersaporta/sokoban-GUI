package view;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import commons.Level;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends java.util.Observable implements Initializable, IView {
	
	char[][] levelData ={
			{' ',' ',' ','#','#','#','#'},
			{' ',' ',' ','#','A',' ','#'},
			{' ',' ',' ','#',' ',' ','#'},
			{' ',' ',' ','#',' ',' ','#'},
			{'#','#','#','#','@',' ','#'},
			{'#',' ',' ',' ',' ',' ','#'},
			{'#',' ',' ',' ','o',' ','#'},
			{'#','#','#','#','#','#','#'}
			};
	
	@FXML
	SokobanDisplayer sokobanDisplayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sokobanDisplayer.setLevelData(levelData);
		
		sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sokobanDisplayer.requestFocus());
		
		sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.UP){
					setChanged();
					notifyObservers("Move up");
				}
				else if(event.getCode()==KeyCode.DOWN){
					setChanged();
					notifyObservers("Move down");
				}
				else if(event.getCode()==KeyCode.RIGHT){
					setChanged();
					notifyObservers("Move right");
				}
				else if(event.getCode()==KeyCode.LEFT){
					setChanged();
					notifyObservers("Move left");
				}
			}
		});
	}
	

	
	public void openFile(){
		FileChooser fc=new FileChooser();
		fc.setTitle("Open level file");
		fc.setInitialDirectory(new File("./resources/levels"));
		
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Text files","*.txt"),
				new ExtensionFilter("XML files","*.xml"),
				new ExtensionFilter("Object files","*.obj"));
		
		File choosen=fc.showOpenDialog(null);//the window that will stack in the backround-Jbutoon
		
		if(choosen!=null){
			System.out.println(choosen.getPath());
			setChanged();
			notifyObservers("load "+choosen.getPath());
		}
	}
	
	public void saveFile(){
		FileChooser fc=new FileChooser();
		fc.setTitle("Open level file");
		fc.setInitialDirectory(new File("./resources/levels"));
		
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Text files","*.txt"),
				new ExtensionFilter("XML files","*.xml"),
				new ExtensionFilter("Object files","*.obj"));
		
		File choosen=fc.showSaveDialog(null);//the window that will stack in the backround-Jbutoon
		
		if(choosen!=null)
			System.out.println(choosen.getPath());
	}



	@Override
	public void displayLevel(Level level, OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void start() {
		Thread t= new Thread(new Runnable() {
			
			@Override
			public void run() {
				Application.launch(Main.class);
			}
		});
		t.start();
	}
	
	


	
}
