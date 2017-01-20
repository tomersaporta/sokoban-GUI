package view;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController {
	
	public void openFile(){
		FileChooser fc=new FileChooser();
		fc.setTitle("Open level file");
		fc.setInitialDirectory(new File("./resources/levels"));
		
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Text files","*.txt"),
				new ExtensionFilter("XML files","*.xml"),
				new ExtensionFilter("Object files","*.obj"));
		
		File choosen=fc.showOpenDialog(null);//the window that will stack in the backround-Jbutoon
		
		if(choosen!=null)
			System.out.println(choosen.getPath());
	}
}
