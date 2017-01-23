		package view;
		
		import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import commons.Level;
import javafx.application.Application;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
		
		public class MainWindowController extends java.util.Observable implements Initializable, IView {
			
			
		@FXML
		private SokobanDisplayer sokobanDisplayer;
			
		@FXML
		private Text countSteps;
			
		//Timer
		@FXML
		private Text timerText;
		private Timer timer;
		int secCount, minCount;
		StringProperty timerCount;
		
		@FXML
		private Button exitButton;
		
				
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
					if(secCount<10)
					timerCount.set(""+(minCount)+":0"+(secCount));
					else
					timerCount.set(""+(minCount)+":"+(secCount));
				}
			}, 0, 1000);		

		}
		
		@Override			
		public void createBindSteps(StringProperty Counter){
			this.countSteps.textProperty().bind(Counter);
		}
		
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
			stopTimer();
			initTimer();
			

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
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Finish Level");
					alert.setContentText("Congratulations!! ");
					alert.setContentText("Steps: "+ countSteps.getText()+"\n Time: "+timerText.getText());
					alert.show();
					
				}
			});
			stopTimer();
		}
		private void stopTimer(){
			if(timer!=null)
				timer.cancel();
			
		}
		
		public void exitWindow(){
			
			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Exit");
			alert.setContentText("Exit Sokoban?");
			
			Optional<ButtonType> result=alert.showAndWait();
			
			if(result.get().getText().equals("OK")){
				setChanged();
				notifyObservers("exit");
				Stage stage = new Stage();
				Parent root;
			
				try 
				{
					root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
					stage.setScene(new Scene(root));
					stage.initModality(Modality.APPLICATION_MODAL);
					stage=(Stage)exitButton.getScene().getWindow();
					stage.close();
				} 
				catch (IOException e) 
				{	
					e.printStackTrace();
				}
			
				
				
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
			
			if(level.isEndOfLevel()){
				finishLevel();
				notifyObservers("exit");
				
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
	
	
		
	}
