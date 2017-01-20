package view;

import java.io.IOException;
import java.io.OutputStream;

import commons.Level;

public interface IView {
	
	public void displayLevel(Level level,OutputStream out) throws IOException;
}
