package TextStuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {
	int porcent;
	int howMuchSections = 5;
	int maxStringSize = 10;
	int minStringSize = 1;
	int sectionSize;
	ArrayList<String> words = new ArrayList<String>();
	ArrayList<SampleText> samples = new ArrayList<SampleText>();

	public TextAnalyzer(String path){
		try {
			getWords(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
					samples.add(newSample);
					actualPorcent++;
				}word++;
			}word =sectionSize*(section+1);
		}
		for(SampleText sample:samples) {
			System.out.print(sample.block);
			System.out.print(sample.word);
			System.out.print(" ");


		}
	}
	private void getWords(String path) throws IOException{
		String text = new String(Files.readAllBytes(Paths.get(path))); 
        Pattern p = Pattern.compile("[a-zA-Z]+"); 
        Matcher m2 = p.matcher(text); 
        while (m2.find()) {
        	words.add(m2.group().toString());
        }
        System.out.println(words.size()*30/100);
	}
}
