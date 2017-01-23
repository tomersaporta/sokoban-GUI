package view;

import java.io.IOException;
import java.io.OutputStream;

import commons.Level;
import javafx.beans.property.StringProperty;

public interface IView {
	
	public void displayLevel(Level level,OutputStream out) throws IOException;
	
	public void start();
	
	public void displayGUI(Level level);
	
	public void createBindSteps(StringProperty Counter);
}
