package TextStuff;




public class main {

	public static void main(String[] args) {
		TextAnalyzer text = new TextAnalyzer("C:\\Users\\"+"Esteban Madrigal"+"\\Documents\\GitHub\\"+"Femme-coccyx"+"\\src\\text1.txt");
		for(int leaf = 0; leaf<text.bst.getElement("t").myPrintTree().size() ; leaf++) {
			for(LinkBP sample: text.bst.getElement("t").myPrintTree().get(leaf)) {
				//System.out.println(sample.getRepeatWordsCound()+sample.getElement().getWord()+sample.getElement().getBlock());
			}
		}
		Azure azure = new Azure("https://copperblackservices.com/wp-content/uploads/2018/08/DTvUQor.jpg");
	
	}
}
