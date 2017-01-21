package view;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import commons.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends java.util.Observable implements Initializable, IView {
	
	
	
	@FXML
	private SokobanDisplayer sokobanDisplayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sokobanDisplayer.requestFocus());
		
		sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			
			@Override
			public void handle(KeyEvent event) {
				String commandInput=null;
				if(event.getCode()==KeyCode.UP){
					commandInput="Move up";
				}
				else if(event.getCode()==KeyCode.DOWN){
					commandInput="Move down";
				}
				else if(event.getCode()==KeyCode.RIGHT){
					commandInput="Move right";
				}
				else if(event.getCode()==KeyCode.LEFT){
					commandInput="Move left";
				}
				setChanged();
				notifyObservers(commandInput);
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
		
		setFocus();
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
		
		if(choosen!=null){
			System.out.println(choosen.getPath());
			setChanged();
			notifyObservers("save "+choosen.getPath());
		}
			
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



	@Override
	public void displayGUI(Level level) {
		sokobanDisplayer.setLevelData(level.getLevelBored());
		sokobanDisplayer.redraw();
	}
	
	private void setFocus()
	{
		sokobanDisplayer.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) 
            {
                Platform.runLater(new Runnable()
                {
                    public void run() 
                    {
                    	sokobanDisplayer.requestFocus();
                    }
                });                    
            }
        });
	}


	
}
