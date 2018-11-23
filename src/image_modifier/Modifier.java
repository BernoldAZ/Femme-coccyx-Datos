package image_modifier;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import TextStuff.TextAnalyzer;
import build.Arc;
import image_analysis.SampleImage;
import lib.iConstants;

public class Modifier implements iConstants{
	
	private List<String> tags = new ArrayList<String>();
	private BufferedImage imageToModify;
	
	private List<Hashtable<String,SampleImage>> listSamplesbyBlockMST;
	private List<Hashtable<SampleImage,Arc>> listSamplesbyBlockRoads;
	
	private TextAnalyzer textStuff;
	
	public Modifier(List<String> pTags, BufferedImage pImageToModify, List<Hashtable<String,SampleImage>> pListSamplesbyBlockMST, List<Hashtable<SampleImage,Arc>> pListSamplesbyBlockRoads, TextAnalyzer pTextStuff) {
		tags = pTags;
		imageToModify = pImageToModify;
		listSamplesbyBlockMST = pListSamplesbyBlockMST;
		listSamplesbyBlockRoads = pListSamplesbyBlockRoads;
		textStuff = pTextStuff;
	}
	
	
	public void makeModifications() {
		ExecutorService service = Executors.newFixedThreadPool(numThreads);
		for (String tag : tags) {
			Thread thread = new Thread(buscarTag(tag));
			
			service.execute(thread);
		}
		
		
	}
	
	private Runnable buscarTag(String pTag) {
		
		textStuff.bst.getElement(pTag);
		return null;
	}

}
