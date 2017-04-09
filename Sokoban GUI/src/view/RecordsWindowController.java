package view;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import commons.Record;
import javafx.fxml.Initializable;

public class RecordsWindowController extends Observable implements Initializable{
	
	public RecordsWindowController() {
		// TODO Auto-generated constructor stub
	}
	
	public void showRecordsTable(List<Record> records){
		
	}

	public void search(){
		setChanged();
		notifyObservers("Query level4 null steps");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("init");
		
	}
}
