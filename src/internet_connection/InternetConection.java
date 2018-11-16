package internet_connection;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.ImageIcon;

import TextStuff.TextAnalyzer;
import Trees4Text.BPlus;

public class InternetConection {
	
	public Image getImage(String pPath) throws IOException {
		
		URL url = new URL(pPath);
		URLConnection con = url.openConnection();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	 
	    String linea;
	    /*
	    while ((linea = in.readLine()) != null) {
	    	System.out.println(linea);
	    	}*/
	      
	    if (con.getContentType() != null) {
	    	String ex = con.getContentType();
	    	System.out.println(ex);            
            ImageIcon icon = new ImageIcon(url);
            
            Image image = icon.getImage();
            return image;
	    	}
		return null;	 
	}
	
	public static void main(String[] args) throws IOException {
       TextAnalyzer text = new TextAnalyzer("C:\\Users\\"+"Esteban Madrigal"+"\\Documents\\GitHub\\"+"Femme-coccyx"+"\\src\\text1.txt");
		BPlus bPTree= new BPlus();
		bPTree.insert(1);
		bPTree.insert(2);
		bPTree.insert(3);
		bPTree.insert(4);
		bPTree.insert(5);
		bPTree.insert(6);
		bPTree.insert(7);
		bPTree.insert(8);
		bPTree.insert(9);
		bPTree.insert(5);
		bPTree.insert(1);
		
		System.out.println();
		System.out.println(bPTree.printTree());
		System.out.println(bPTree.root.toString());
		System.out.println(bPTree.search(4).getClass());
		
    }
}
