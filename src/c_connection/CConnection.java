package c_connection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import com.google.gson.Gson;

import image_analysis.SampleImage;

public class CConnection {
	
	String Json;
	
	public void requestToC(List<SampleImage> pListSamples) throws IOException {
		
		Gson json = new Gson();
		
		String jsonToC = json.toJson(pListSamples);
		
		String ruta = "SendToC.txt";
		File archivo = new File(ruta);
		BufferedWriter bw;
		
		if(archivo.exists()) {
		      bw = new BufferedWriter(new FileWriter(archivo));
		      bw.write(jsonToC);
		} else {
		      bw = new BufferedWriter(new FileWriter(archivo));
		      bw.write(jsonToC);
		}
		
	}
	
	public void responseFromC() {
		
	}
	
}
