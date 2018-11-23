package build;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import c_connection.Grafo;
import c_connection.Node;
import image_analysis.SampleImage;
import lib.iConstants;

public class RoadHash implements iConstants {
	

	private List<Hashtable<SampleImage,Arc>> listSamplesbyBlock = new ArrayList<Hashtable<SampleImage,Arc>>();
	
	public RoadHash(Node[] pSamplesMin, Node[] pSamplesMax) {	
		
		for (int numBlock = 0; numBlock < NumberOfBlocks; numBlock++) {//Instancia los hash
			Hashtable<SampleImage,Arc> SamplesInBlock = new Hashtable<SampleImage,Arc> ();
			listSamplesbyBlock.add(SamplesInBlock);
		}
		
		
		for (int pos = 0; pos< pSamplesMin.length; pos++) {
			Node sampleInicial = pSamplesMin[pos];
			int peso = sampleInicial.getPeso() + pSamplesMax[pos].getPeso();
			Arc newArc = new Arc(sampleInicial, pSamplesMax[pos], peso);
			listSamplesbyBlock.get(sampleInicial.getValue().getBlock()).put(sampleInicial.getValue(), newArc);//Coloca en el hash de la posición del bloque

		}
		
	}




	private int [][] Pesos;
	private int [] ultimo;
	private int [] D;
	private boolean [] F;
	private int s, n; // vértice origen y número de vértices
	public RoadHash(Grafo gp, int origen)
	{
	n = 3;
	s = origen;
	ultimo = new int [n];
	D = new int [n];
	F = new boolean [n];
	}

	public void caminoMinimos()
	{
		// valores iniciales
		for (int i = 0; i < n; i++)
		{
			F[i] = false;
			D[i] = Pesos[s][i];
			ultimo[i] = s;
		}
		F[s] = true; D[s] = 0;
		// Pasos para marcar los n-1 vértices
		for (int i = 1; i < n; i++)
		{
			int v = minimo(); /* selecciona vértice no marcado
									de menor distancia */
			F[v] = true;
			// actualiza distancia de vértices no marcados
			for (int w = 1; w < n; w++)
				if (!F[w])
					if ((D[v] + Pesos[v][w]) < D[w])
					{
						D[w] = D[v] + Pesos[v][w];
						ultimo[w] = v;
					}
		}
	}
	
	private int minimo()
	{
		int mx = Infinito;
		int v = 1;
		for (int j = 1; j < n; j++)
			if (!F[j] && (D[j]<= mx))
			{
				mx = D[j];
				v = j;
			}
		return v;
	}

}
