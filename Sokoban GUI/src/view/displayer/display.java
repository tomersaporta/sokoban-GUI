package view.displayer;

import java.io.IOException;
import java.io.OutputStream;

import model.data.Level;

/**
 * <h1>display interface</h1>
 * defines the behavior that all the displayers needs to implement
 *
 */

public interface display {
	
	public void displayLevel(Level level,OutputStream out) throws IOException;
}
