package image_analysis;


import java.util.List;

import internet_connection.Tag;

public class Tagger {
	private List<Tag> listTags;
	private int[] amountPerTag; //Es la cantidad de veces que se va a poner un tag
	private int posInListTags;

	public Tagger(List<Tag> pListTags, int pMaxSamples) {
		System.out.println(pMaxSamples);
		listTags = pListTags;
		posInListTags = 0;
		
		amountPerTag = new int[pListTags.size()];
		for (int pos = 0; pos < amountPerTag.length; pos++) {
			amountPerTag[pos] = pMaxSamples / amountPerTag.length; //Divide la cantidad maxima entre el numero de tags, para asignarle la misma cantidad a todos
		}
		setTrueAmount();		
	}
	
	public String getTag() {
		return listTags.get(posInListTags).getName();
	}
	
	private void setTrueAmount() {
		int recorrerDerecho = 0;
		int recorrerReves = listTags.size() -1;
		double percentage = 0.30;
		while (recorrerDerecho <= recorrerReves) {
			int alterar = (int) (amountPerTag[recorrerDerecho] * percentage );
			amountPerTag[recorrerDerecho] =  (amountPerTag[recorrerDerecho] + alterar);
			amountPerTag[recorrerReves] = (amountPerTag[recorrerReves] - alterar);
			recorrerDerecho++;
			recorrerReves--;
			percentage = percentage - 0.03;
		}
	}
	
}
