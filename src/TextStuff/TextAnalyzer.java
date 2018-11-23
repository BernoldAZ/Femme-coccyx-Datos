package TextStuff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TextStuff.BPlusTree;

public class TextAnalyzer {
	int porcent;
	int howMuchSections = 5;
	int maxStringSize = 10;
	int minStringSize = 1;
	int sectionSize;
	ArrayList<String> words = new ArrayList<String>();
	ArrayList<SampleText> samples = new ArrayList<SampleText>();
	public BTreeSet bst;

	public TextAnalyzer(String path){
		bst = new BTreeSet(5);
		try {
			getWords(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sectionSize = words.size()/howMuchSections;
		porcent = sectionSize*30/100;
		int word = 0;
		for(int section = 0; section<howMuchSections;section++) {
			int actualPorcent = 0;
			while(actualPorcent<porcent) {
				if(words.get(word).length()<=maxStringSize && words.get(word).length()>=minStringSize ) {
					SampleText newSample = new SampleText(words.get(word).toLowerCase(),section);
					if(bst.getElement(String.valueOf(newSample.getWord().charAt(0))) == null){
						BPlusTree newTree = new BPlusTree(String.valueOf(newSample.getWord().charAt(0)));
						bst.add(newTree);
					}
					bst.getElement(String.valueOf(newSample.getWord().charAt(0))).insert(newSample);
					actualPorcent++;
				}word++;
			}word = sectionSize*(section+1);
		}		
	}
	private void getWords(String path) throws IOException{
		String text = new String(Files.readAllBytes(Paths.get(path))); 
        Pattern p = Pattern.compile("[a-zA-Z]+"); 
        Matcher m2 = p.matcher(text);
        while (m2.find()) {
        	words.add(m2.group().toString());
        }
	}
	public void showAll() {
		String[] abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	}
}
