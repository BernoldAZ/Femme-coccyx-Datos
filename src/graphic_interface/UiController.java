package graphic_interface;

import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

import TextStuff.TextAnalyzer;
import image_analysis.ImageAnalyzer;
import internet_connection.Azure;
import internet_connection.InternetConection;
import internet_connection.Tag;

public class UiController {
	private String path;
	private List<Tag> listTags;
	private ImageIcon image;
	
	private static UiController controller = null;
	
	private UiController() {}
	
	public static UiController getInstance() {
		if (controller == null) {
			controller = new UiController();
		}
		return controller;
	}
	
	public ImageIcon loadImage (String pPath) throws IOException {
		path = pPath;
		InternetConection cargar = new InternetConection();
		image = cargar.getImage(pPath);
		return image;
	}
	
	public List<Tag> getTags(){
		Azure azure = new Azure(path);
        listTags = azure.getTags();
		return listTags;
	}
	
	public ImageIcon analizeImage(Image pImage) {
		ImageAnalyzer analyzer = new ImageAnalyzer(pImage, listTags);
		ImageIcon alterado = new ImageIcon(analyzer.getImageToAnalize());
		return alterado;
	}
	
	public void analizeText() {
		TextAnalyzer text = new TextAnalyzer("C:\\Users\\usuario\\Desktop\\TEC\\Estructuras de datos\\proyecto 2\\Femme-coccyx Datos\\src\\text1.txt");
        
	}

}
