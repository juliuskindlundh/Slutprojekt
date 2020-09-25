package BildHanterare;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

public class ImageHandler {
	
	public Image image;
	public ImageView imageView;
	public double xBound;
	public double yBound;
	public String filePath;
	public ActionHandlers actionHandler;
	String tempFile = "D:\\tempFile.jpg";
	
	
	ImageHandler(String filePath,double xBound,double yBound,ActionHandlers ah) throws IOException{
		
		//Load either the placeholder image or the selected file
		if(filePath.equals("PLACEHOLDER")) {
			this.image = new Image(getClass().getResourceAsStream("/BildHanterare/Bilder/Placeholder.png"));
		}
		else {
			this.image = new Image(new FileInputStream(filePath));
		}
		
		actionHandler = ah;
		this.imageView = new ImageView(image);
		this.xBound = xBound;
		this.yBound = yBound;
		this.filePath = filePath;
		rotateImage(0); 
		scaleImage(1);
	}
	
	//This Method resizes an image so that it fits within the bounds
	//The high /width proportions stay the same
	public void scaleImage(double scale) {
		if(image.getHeight() <= image.getWidth()) {
			double newX = image.getWidth() * yBound * scale / image.getHeight();
			imageView.setFitWidth(newX);
			imageView.setFitHeight(yBound*scale);
		}
		else {
			double newY = image.getHeight() * xBound * scale/ image.getWidth();
			imageView.setFitHeight(newY);
			imageView.setFitWidth(xBound*scale);
		}
		
	}
	
	public ImageView getImageView() {
		return this.imageView;
	}
	
	//This method changes the rgb values of the image
	public void changeRGB(double R, double G, double B) throws IOException {
		BufferedImage buffImg;
		if(filePath.equals("PLACEHOLDER")) {
			buffImg = ImageIO.read(getClass().getResourceAsStream("/BildHanterare/Bilder/Placeholder.png"));
		}
		else {
			buffImg = ImageIO.read(new File(filePath));
		}
		//iterate through the entire image and change the rgb value of each pixel
		for(int i = 0; i < buffImg.getWidth();i++) {
			for(int k = 0; k < buffImg.getHeight();k++) {				
				int pixel = buffImg.getRGB(i,k);
				Color color = new Color(pixel);
				double red = color.getRed() * R;
				double green= color.getGreen() * G;
				double blue = color.getBlue() * B;			
				Color newColor = new Color((int)red,(int)green,(int)blue);
				buffImg.setRGB(i, k, newColor.getRGB());
			}
		}
		saveAs("qwer",buffImg);
	}
	
	public void rotateImage(int r) {
		actionHandler.rotation = actionHandler.rotation + r;
		imageView.setRotate(actionHandler.rotation);
		if(actionHandler.rotation == 360) {
			actionHandler.rotation = 0;
		}
	}
	
	private void setRotation(int r) throws IOException {
		imageView.setRotate(r);
	}
	
	public void saveAs(String name,BufferedImage buffImg) throws IOException {		
		//Get the file path to the temporary version of the image 
		//I have a blank 
		String fp =getClass().getResource("/BildHanterare/Bilder/TEMP.png").getFile().replaceAll("%20", " ");		
		File file = new File(fp);
		ImageIO.write(buffImg, "png", file);
		actionHandler.filePath = file.getAbsolutePath();
		image = new Image(new FileInputStream(file.getAbsolutePath()));	    
		imageView = new ImageView(image);
		
		//set the scale and rotation of the new image
	    scaleImage(1);
	    setRotation(actionHandler.rotation);
	}
	
}
