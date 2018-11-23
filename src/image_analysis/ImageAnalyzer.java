package image_analysis;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import c_connection.CConnection;
import internet_connection.Tag;
import lib.iConstants;

public class ImageAnalyzer implements iConstants{
	
	private Hashtable<String,SampleImage> HashSamples = new Hashtable<String,SampleImage>(); //Esta ordenado por el rgb y #bloque
	private List<SampleImage> ListSamplesOrganized = new ArrayList<SampleImage>(); //Esta ordenado por el ID unico
	private BufferedImage ImageToAnalize;
	private List<Tag> listTags;
	
	public Hashtable<String,SampleImage> getHashSamples() {
		return HashSamples;
	}

	public List<SampleImage> getListSamples() {
		return ListSamplesOrganized;
	}

	public ImageAnalyzer(Image pImage, List<Tag> pListTags) {
		ImageToAnalize = toBufferedImage(pImage);
		listTags = pListTags;
		getSamples();
	}
	
	private BufferedImage toBufferedImage(Image pImage)
	{
	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(pImage.getWidth(null), pImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(pImage, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	private void getSamples() {
		int heightForBlock = ImageToAnalize.getHeight() / NumberOfBlocks; //Alto
		int widthForBlock = ImageToAnalize.getWidth(); //Ancho
		int allElementsPerBlock = heightForBlock * widthForBlock;
		
		Integer uniqueID = 1;
		
		Tagger tagger = new Tagger(listTags, (int) (allElementsPerBlock /0.15)); //
		
		for (Integer block = 0; block < NumberOfBlocks; block++) {
			double randomNumber = (Math.random() * 5) + 10; //Da un numero random entre 10 y 15
			double percentage = randomNumber / 100;
			int allSamplesPerBlock = (int) (allElementsPerBlock * percentage);
			
			
			int minPosY = heightForBlock*block;
			int maxPosY = heightForBlock*(block+1);
			int differenceY = maxPosY - minPosY;
			
			for (int sampleCounter = 0; sampleCounter < allSamplesPerBlock; sampleCounter++) {
				int randomX = (int) (Math.random() * widthForBlock) + 0;
				int randomY = (int) (Math.random() * differenceY) + minPosY;
				Integer rgb = ImageToAnalize.getRGB(randomX, randomY);
				String hashKey = rgb.toString()  + block.toString();
				if( HashSamples.get(hashKey) == null) { //Entonces crea un sample nuevo
					SampleImage newSample = new SampleImage(uniqueID,randomX,randomY, block,  rgb);
					//Falta agregar los tags
					HashSamples.put(hashKey, newSample);
					ListSamplesOrganized.add(newSample);
					uniqueID++;
				}
				else {
					int repetitions = HashSamples.get(hashKey).getRepetitions();
					HashSamples.get(hashKey).setRepetitions(repetitions++); //Aumenta el contador en el ordenado por RGB y bloque
					int posID = HashSamples.get(hashKey).getID();
					ListSamplesOrganized.get(posID-1).setRepetitions(repetitions++); //Ordenado por ID
					
				}
			}
		}
		
		CConnection das = new CConnection();
		
		try {
			das.requestToC(ListSamplesOrganized);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* es para ver cada elemento de las hash table
		Enumeration<SampleImage> llaves = HashSamples.elements();
		while (llaves.hasMoreElements()) {
				System.out.println(""+"hashtable llave: " + llaves.nextElement().getRepetitions());
		}
		System.out.println(uniqueID);
		*/
		
	}

	public BufferedImage getImageToAnalize() {
		return ImageToAnalize;
	}
	
}
