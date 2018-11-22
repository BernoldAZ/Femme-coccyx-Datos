package TextStuff;


public class main {

	public static void main(String[] args) {
		TextAnalyzer text = new TextAnalyzer("C:\\Users\\"+"Esteban Madrigal"+"\\Documents\\GitHub\\"+"Femme-coccyx"+"\\src\\text1.txt");
		String[] abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		System.out.println(text.words.size()*30/100);
		int totalSamples = 0;
		for(String word: abc) {
			if(text.bst.getElement(word) != null) {
				for(int leaf = 0; leaf < text.bst.getElement(word).myPrintTree().size(); leaf++) {
					for(int count = 0; count<text.bst.getElement(word).myPrintTree().get(leaf).size();count++) {
						totalSamples = totalSamples+ Integer.valueOf(text.bst.getElement(word).myPrintTree().get(leaf).get(count));
					}
				}
			}
		}
		System.out.println(totalSamples);

	}

}
