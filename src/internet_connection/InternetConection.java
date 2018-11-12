package internet_connection;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.ImageIcon;

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
		double numero = (Math.random() * 5) + 10;
		System.out.println(numero/100);
		
    }
}
