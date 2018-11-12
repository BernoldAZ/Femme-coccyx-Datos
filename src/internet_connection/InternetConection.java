package internet_connection;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import image_analysis.SampleImage;

public class InternetConection {
	
	public ImageIcon getImage(String pPath) throws IOException {
		
		URL url = new URL(pPath);
		URLConnection con = url.openConnection();
		
	      
	    if (con.getContentType() != null) {
	    	String ex = con.getContentType();
	    	System.out.println(ex);            
            ImageIcon icon = new ImageIcon(url);
            
            //Image image = icon.getImage();
            return icon;
	    	}
		return null;	 
	}
	
	public void askAzure() {
		
	}
	
	public static void main(String[] args) throws IOException {
		Hashtable<Integer,String> HashSamples = new Hashtable<Integer,String>();
		HashSamples.put(1, "hola");
		System.out.println(HashSamples.get(2));
		
    }
}
