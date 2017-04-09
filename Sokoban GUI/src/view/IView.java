package view;

import commons.Level;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public interface IView {
	
	public void displayGUI(Level level);
	
	public void createBindSteps(StringProperty Counter);
	
	public void setPrimaryStage(Stage primaryStage);
	
	public void exitPrimaryStage();
	
	public void displayError(String error);

	public void setSecondStage(Stage secondStage);
}
