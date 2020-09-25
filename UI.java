package BildHanterare;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UI {
	
	//Create all ui elements
	BorderPane pane = new BorderPane();
	Scene scene = new Scene(pane);
	ImageHandler imageHandler;
	ActionHandlers actionHandler;
	
	TextField tfRed = new TextField("1");
	TextField tfGreen = new TextField("1");
	TextField tfBlue = new TextField("1");
	Text tR = new Text("Red");
	Text tG = new Text("Green");
	Text tB = new Text("Blue");
	Text tAboveRGB = new Text("Values between 0.0 and 1.0 get accepted");
	Text tSize = new Text("Change size");
	
	Button btnOK = new Button("Apply changes");
	Button btnRotateR = new Button("Rotate +90");
	Button btnLoadImg = new Button("Load image");
	Button btnSizeM = new Button(" - ");
	Button btnSizeP = new Button(" + ");
	
	GridPane rigthGridPane = new GridPane();
	GridPane rigthGridPaneSub1 = new GridPane();
	
	FileChooser fileChooser = new FileChooser();
	Button fileChooserBtn = new Button("Select file");
	
	UI(Stage primaryStage,String filePath, ActionHandlers actionHandler) throws IOException{
		
		//Add all ui elements the the scene
		this.actionHandler = actionHandler;
		primaryStage.setMaximized(true);
		imageHandler = new ImageHandler(filePath,900,900,actionHandler);	
		rigthGridPane.setHgap(10);
		rigthGridPane.setVgap(10);
		rigthGridPaneSub1.setHgap(10);
		
		int column = 0;
		pane.setRight(rigthGridPane);
		rigthGridPane.add(fileChooserBtn,1, column++);
		rigthGridPane.add(tAboveRGB, 1, column++);
		rigthGridPane.add(tfRed, 1, column++);
		rigthGridPane.add(tfGreen, 1, column++);
		rigthGridPane.add(tfBlue, 1, column++);
		rigthGridPane.add(btnOK, 1, column++);		
		rigthGridPane.add(btnRotateR, 1, column++);
		rigthGridPane.add(rigthGridPaneSub1, 1, column++);
		rigthGridPaneSub1.add(btnSizeM, 0, 0);
		rigthGridPaneSub1.add(btnSizeP, 1, 0);
		
		column = 1;
		column++;
		rigthGridPane.add(tR, 0, column++);
		rigthGridPane.add(tG, 0, column++);
		rigthGridPane.add(tB, 0, column++);
		column = column +2;
		rigthGridPane.add(tSize, 0, column++);
		
		//Set all ui functions
		this.btnOK.setOnMouseClicked(a->{
			actionHandler.okBtn();
			this.pane.setCenter(this.imageHandler.getImageView());
		});		
		
		this.btnRotateR.setOnMouseClicked(a->{
			actionHandler.rotateRbtn();
		});
		
		this.btnLoadImg.setOnMouseClicked(a->{		
			try {
				this.imageHandler.imageView.setImage(null);
				System.gc();
				this.imageHandler = new ImageHandler(actionHandler.loadImg(actionHandler.filePath),actionHandler.limit,actionHandler.limit,actionHandler);
				this.pane.setCenter(this.imageHandler.getImageView());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.btnSizeM.setOnMouseClicked(a->{
			try {
				actionHandler.btnSizeM();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.btnSizeP.setOnMouseClicked(a->{
			try {
				actionHandler.btnSizeP();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.fileChooserBtn.setOnMouseClicked(a->{
			try {
				actionHandler.selectFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public String getFilePath() {
		return this.actionHandler.filePath;
	}
	
	
	
}
