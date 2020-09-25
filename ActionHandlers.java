package BildHanterare;

import java.io.File;
import java.io.IOException;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ActionHandlers {
	
	UI ui;
	int limit = 900;
	int rotation = 0;
	String filePath;
	
	ActionHandlers(){}
	
	//This method applies the changes in rgb values to the image
	public void okBtn() {
		try {
			//Get the values used to scale the rgb values of the image
			double red = Double.parseDouble(ui.tfRed.getText());
			double green = Double.parseDouble(ui.tfGreen.getText());
			double blue= Double.parseDouble(ui.tfBlue.getText());			
			if(red >= 0 && red <= 1 && green >= 0 && green <= 1 && blue >= 0 && blue <= 1) {
				//remove the old image
				ui.imageHandler.imageView.setImage(null);
				System.gc();
				//set a new image with the modified rgb values
				ui.imageHandler.changeRGB(red, green, blue);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//This method rotates the image +90 degrees
	public void rotateRbtn() {
		ui.imageHandler.rotateImage(90);
	}
	
	//This method verifies that a filepath corresponds to a readable file and then either sets "filePath" to that value or to "PLACEHOLDER"
	public String loadImg(String fp) {
		File f = new File(fp);		
		if(f.exists() && f.canRead()) {
			filePath = fp;
			return fp;
		}
		else {
			return "PLACEHOLDER";
		}
	}
	
	//This Method reduces the size of a image
	public void btnSizeM() throws IOException {
		limit = limit-50;
		if(limit <= 0) {
			limit = 50; 
		}
		//Remove the old image
		ui.imageHandler.imageView.setImage(null);
		System.gc();
		//set a new image that conforms to the new limits
		ui.imageHandler = new ImageHandler(this.loadImg(filePath),this.limit,this.limit,this);
		ui.pane.setCenter(ui.imageHandler.getImageView());
	}

	//This Method increases the size of a image
	public void btnSizeP() throws IOException {
		limit = limit+50;
		if(limit >= 1200) {
			limit = 1200; 
		}
		//Remove the old image
		ui.imageHandler.imageView.setImage(null);
		System.gc();
		//set a new image that conforms to the new limits
		ui.imageHandler = new ImageHandler(this.loadImg(filePath),this.limit,this.limit,this);
		ui.pane.setCenter(ui.imageHandler.getImageView());
		
	}

	public void selectFile() throws IOException {
		File file = ui.fileChooser.showOpenDialog(null);
			if(file != null) {
				filePath = file.getAbsolutePath();
				ui.imageHandler.imageView.setImage(null);
				System.gc();
				ui.imageHandler = new ImageHandler(this.loadImg(filePath),this.limit,this.limit,this);
				ui.pane.setCenter(ui.imageHandler.getImageView());
				
			}
	}
}
