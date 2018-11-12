package image_analysis;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.List;

import lib.iConstants;

public class ImageAnalyzer implements iConstants{
	
	private Hashtable HashSamples;
	private List<SampleImage> ListSamples;
	private BufferedImage ImageToAnalize;
	
	public Hashtable getHashSamples() {
		return HashSamples;
	}

	public List<SampleImage> getListSamples() {
		return ListSamples;
	}

	public ImageAnalyzer(Image pImage) {
		ImageToAnalize = toBufferedImage(pImage);
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
		
		for (int block = 0; block < NumberOfBlocks; block++) {
			double randomNumber = (Math.random() * 5) + 10; //Da un numero random entre 10 y 15
			double percentage = randomNumber / 100;
			int allSamplesPerBlock = (int) (allElementsPerBlock * percentage);
			
			
			int minPosY = heightForBlock*block;
			int maxPosY = heightForBlock*(block+1);
			int differenceY = maxPosY - minPosY;
			
			for (int sampleCounter = 0; sampleCounter < allSamplesPerBlock; sampleCounter++) {
				int randomX = (int) (Math.random() * widthForBlock) + 0;
				int randomY = (int) (Math.random() * differenceY) + minPosY;
				//ImageToAnalize.getRGB(randomX, randomY);
				ImageToAnalize.setRGB(randomX, randomY, 0);
				
			}
		}
		
		
		
	}

	public BufferedImage getImageToAnalize() {
		return ImageToAnalize;
	}
	
}
