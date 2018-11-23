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

import graphic_interface.UiView;
import image_analysis.SampleImage;

public class CConnection {
	
	private Node sampleMaxRepetitions;
	private Node[] samplesMin;
	private Node[] samplesMax;
	
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
		
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(new FileReader("SendToJava.txt"));
        JsonObject jsonObject = (JsonObject) obj;
        	       
        JsonArray nodes = (JsonArray) jsonObject.get("nodes");
        
        int listNodesSize = nodes.size();
        int tenPercentage = (int) (listNodesSize * 0.1);
        samplesMin = new Node[tenPercentage];
        samplesMax = new Node[tenPercentage];
        
        
        for(int i = 0; nodes.size() > i ; i++) {
        	JsonObject nodeFromFile = (JsonObject) nodes.get(i);			
			Node newNode = gson.fromJson(nodeFromFile, Node.class);
			//Falta guardar los nodos en algun lado, llenar las listas de max y min
			
			//Saca el nodo con el mayor numero de repeticiones en el sample
			if (sampleMaxRepetitions != null) {
				if (sampleMaxRepetitions.getValue().getRepetitions() < newNode.getValue().getRepetitions()) {
					sampleMaxRepetitions = newNode;
				}
			}
			else {
				sampleMaxRepetitions = newNode;
			}
						
        }
    }

	
	public static void main(String[] args) throws FileNotFoundException { 
        CConnection prueba = new CConnection();
        
        prueba.readJson();
    } 
	
}
