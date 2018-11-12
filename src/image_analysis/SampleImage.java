package image_analysis;

public class SampleImage {
	private int ID;
	private int PosX;
	private int PosY;
	private String Tag;
	private int Block;
	private int RGB;
	private int Repetitions;
	
	public SampleImage(int pID, int pPosX, int pPosY, int pBlock, int pRGB) {
		Repetitions = 1;
		ID = pID;
		PosX = pPosX;
		PosY = pPosY;
		Block = pBlock;
		RGB = pRGB;
		
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public int getRepetitions() {
		return Repetitions;
	}

	public void setRepetitions(int repetitions) {
		Repetitions = repetitions;
	}

	public int getID() {
		return ID;
	}

	public int getPosX() {
		return PosX;
	}

	public int getPosY() {
		return PosY;
	}

	public int getBlock() {
		return Block;
	}

	public int getRGB() {
		return RGB;
	}
	

}
