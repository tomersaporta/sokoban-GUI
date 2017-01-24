		package view;
		
		import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import commons.Level;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
		
		public class MainWindowController extends java.util.Observable implements Initializable, IView {		
			
		@FXML
		private SokobanDisplayer sokobanDisplayer;
			
		//Steps
		@FXML
		private Text countSteps;
			
		//Timer
		@FXML
		private Text timerText;
		private Timer timer;
		int secCount, minCount;
		StringProperty timerCount;
		
		//Exit
		//@FXML
		//private Button exitButton;
		
		//finishLevel
		private boolean isFinished;
		
		//Keyboard settings;
		private ViewSettings viewSettings;
		
		//Stage
		private Stage primaryStage;
		
		public MainWindowController() {
			this.isFinished=false;
			this.viewSettings=initViewSettings("./resources/viewSettings/viewSettings.xml");
		}
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			setFocus();
			
			sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sokobanDisplayer.requestFocus());
			
			sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
	
				 
				@Override
				public void handle(KeyEvent event) {
					String commandInput=null;
					if(event.getCode()==viewSettings.getMoveUp()){
						commandInput="Move up";
					}
					else if(event.getCode()==viewSettings.getMoveDown()){
						commandInput="Move down";
					}
					else if(event.getCode()==viewSettings.getMoveRight()){
						commandInput="Move right";
					}
					else if(event.getCode()==viewSettings.getMoveLeft()){
						commandInput="Move left";
					}
					
					setChanged();
					notifyObservers(commandInput);
				}
			});
			
		}
				
		private void initTimer(){
			
			this.timerCount=new SimpleStringProperty();
			this.secCount=0;
			this.minCount=0;
			this.timer=new Timer();
			this.timerText.textProperty().bind(this.timerCount);
			
			this.timer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					secCount++;
					if (secCount>59){
						minCount++;
						secCount=0;
					}
					if (minCount<10)
						if(secCount<10)
							timerCount.set("0"+(minCount)+":0"+(secCount));
						else
							timerCount.set("0"+(minCount)+":"+(secCount));
					else
					timerCount.set(""+(minCount)+":"+(secCount));
					
						
				}
			}, 0, 1000);		

		}
		
		private void stopTimer(){
			if(timer!=null)
				timer.cancel();
			
		}
		
		@Override			
		public void createBindSteps(StringProperty Counter){
			this.countSteps.textProperty().bind(Counter);
		}
		
		//GUI CODE
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
			
			//setFocus();
			stopTimer();
			initTimer();
			this.isFinished=false;
			

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
		
		public void finishLevel(){
			if(this.isFinished==true)return;//display popup only one time
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Finish Level");
					alert.setHeaderText("Congratulations!!!");
					alert.setContentText("Steps: "+ countSteps.getText()+"\n Time: "+timerText.getText());
					alert.show();
					
				}
			});
			stopTimer();
			this.isFinished=true;
		}
	
		public void exitWindow(){
			
			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Exit");
			alert.setContentText("Exit Sokoban?");
			
			Optional<ButtonType> result=alert.showAndWait();
			
			if(result.get() == ButtonType.OK){
				setChanged();
				notifyObservers("exit");
				Platform.exit();
			}
			
		}
	
	
		@Override
		public void displayGUI(Level level) {
			sokobanDisplayer.setLevelData(level.getLevelBored());
			
			if(level.isEndOfLevel()){
				this.isFinished=false;
				finishLevel();
				System.out.println("finish");
			}
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
		
		//load XML file
		private ViewSettings initViewSettings(String filepath){
			
			XMLDecoder decoder;
			ViewSettings vs=null;
			try {
				decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File(filepath))));
				vs= (ViewSettings)decoder.readObject();
				decoder.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return vs;
		}

		@Override
		public void setPrimaryStage(Stage primaryStage){
			this.primaryStage= primaryStage;
			exitPrimaryStage();
		}

		@Override
		public void exitPrimaryStage() {
			this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					setChanged();
					notifyObservers("exit");
				}
			});
		}
	}
