package c_connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import graphic_interface.Ui;
import image_analysis.SampleImage;
import c_connection.Node;

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
		      
		      String start = "{" + '"' + "list" + '"' + ':';
		      bw.write(start);
		      bw.write(jsonToC);
		} else {
		      bw = new BufferedWriter(new FileWriter(archivo));
		      bw.write(jsonToC);
		}
		
	}
	
	
	public void readJson() throws FileNotFoundException {
		Gson gson=new Gson();
  	
		Node sampleMaxRepetitions;
		Node[] samplesMin;
		Node[] samplesMax;
		
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(new FileReader("SendToJava.txt"));
        JsonObject jsonObject = (JsonObject) obj;
        
	       
        JsonArray nodes = (JsonArray) jsonObject.get("nodes");
        for(int i = 0; nodes.size() > i ; i++) {
        	JsonObject nodeFromFile = (JsonObject) nodes.get(i);			
			Node newNode = gson.fromJson(nodeFromFile, Node.class);

        }
    }

	
	public static void main(String[] args) throws FileNotFoundException { 
        CConnection prueba = new CConnection();
        
        prueba.readJson();
    } 
	
}
