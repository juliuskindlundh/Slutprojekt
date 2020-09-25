package BildHanterare;

import java.io.FileInputStream;
import java.io.IOException;

import com.sun.glass.ui.Screen;
import com.sun.javafx.geom.Rectangle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BHMain extends Application{
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		ActionHandlers actionHandler = new ActionHandlers();
		String fp = "PLACEHOLDER";
		UI ui = new UI(primaryStage,fp,actionHandler);	
		actionHandler.ui = ui;		
		ui.pane.setCenter(ui.imageHandler.getImageView());
		primaryStage.setTitle("Bild hanterare");
		primaryStage.setScene(ui.scene);
		primaryStage.show();		

	}
}
