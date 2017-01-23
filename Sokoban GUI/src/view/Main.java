package view;
	
import controller.SokobanController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			
			FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();
			MainWindowController view=loader.getController();
			
			MyModel model=new MyModel();
			SokobanController theController= new SokobanController(view, model);
			
			view.addObserver(theController);
			model.addObserver(theController);
			
			Scene scene = new Scene(root,700,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * create the connection between the Model View Controller
	 */	
	public static void main(String[] args) {
		launch(args);
		
	}
}
