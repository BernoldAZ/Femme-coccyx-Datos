package graphic_interface;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import TextStuff.TextAnalyzer;
import c_connection.CConnection;
import image_analysis.ImageAnalyzer;
import image_modifier.Modifier;
import internet_connection.Azure;
import internet_connection.InternetConection;
import internet_connection.Tag;

public class UiController {
	private String path;
	private List<Tag> listTags;
	private ImageIcon image;
	private TextAnalyzer text;
	
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
		text = new TextAnalyzer("C:\\Users\\usuario\\Desktop\\TEC\\Estructuras de datos\\proyecto 2\\Femme-coccyx Datos\\src\\text1.txt");
        
	}
	
	public void modifyImage(Image pImage) {
		
		CConnection cResult = new CConnection();
		
		try {
			cResult.readJson();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> tags = new ArrayList<String>();
		for (Tag tag : listTags) {
			tags.add(tag.getName());
		}
		
		Modifier modifier = new Modifier(tags, toBufferedImage(pImage), cResult.getListSamplesbyBlockMST(), cResult.getListSamplesbyBlockRoads(), text);
	}
	
	private BufferedImage toBufferedImage(Image pImage)
	{
	    BufferedImage bimage = new BufferedImage(pImage.getWidth(null), pImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(pImage, 0, 0, null);
	    bGr.dispose();
	    return bimage;
	}

}
