package view.displayer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import model.data.Level;
import model.data.SokoElements.Box;
import model.data.SokoElements.Player;
import model.data.SokoElements.movable;

/**
 * <h1>Display 2D</h1>
 * Display the level
 *
 */
public class Display2D implements display {

	/**
	 * displays the level on every type of OutputStream
	 */
	@Override
	public void displayLevel(Level myLevel,OutputStream output) throws IOException {
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(output));
		
		//Scanning the two arrays of level and prints to representer of each sokobanEntity
		for(int i=0;i<myLevel.getRow() ;i++){
			for (int j=0;j<myLevel.getCol();j++){
				movable m = myLevel.getMovearr()[i][j];
				if(m instanceof Player || m instanceof Box)
					out.write(m.getType());
				else
					out.write(myLevel.getBackground()[i][j].getType());
				
			}
			if (i == myLevel.getRow() - 1)
			{
				break;
			}
				
			out.newLine();
		}
		
		//out.close();
		out.newLine();
		out.flush();

	}

}
