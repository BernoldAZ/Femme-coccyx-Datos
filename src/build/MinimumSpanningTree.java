package build;


import c_connection.Grafo;
import c_connection.Node;
import lib.iConstants;

public class MinimumSpanningTree implements iConstants {
	
	private Node raiz; //Es el nodo con mayor numero de repeticiones
	
	
	public MinimumSpanningTree (Grafo pGrafo) {	
		Node nodeRaiz = pGrafo.getNodeMaxRepetitions();
		raiz = nodeRaiz;
	}
	
	public void recorrer(Node pNode) {
		if (pNode != null) {
			//Hace lo que se tenga que hacer
			recorrer(pNode.getHijo_Izq());
			recorrer(pNode.getHijo_Der());
		}
		return;
	}
		
	
	private int [][] Pesos;
	private int n; // vértice origen y número de vértices
	
	
	public int arbolExpansionPrim() // implementación del algoritmo
	{
		int longMin, menor;
		int z;
		int [] coste = new int [n];
		int [] masCerca = new int [n];
		boolean [] W = new boolean [n];
		for (int i = 0; i < n; i++)
			W[i] = false; // conjunto vacío
		longMin = 0;
		W[0] = true; //se parte del vértice 0
		
		// inicialmente, coste[i] es la arista (0,i)
		for (int i = 1; i < n; i++)
		{
			coste[i] = Pesos[0][i];
			masCerca[i] = 0;
		}
		for (int i = 1; i < n; i++)
		{ // busca vértice z de V-W mas cercano,
			// de menor longitud de arista, a algún vértice de W
			menor = coste[1];
			z = 1;
			for (int j = 2; j < n; j++)
				if (coste[j] < menor)
				{
					menor = coste[j];
					z = j;
				}
			longMin += menor;
			// se escribe el arco incorporado al árbol de expansión
			System.out.println("V" + masCerca[z] + " -> " + "V" + z);
			W[z] = true; // vértice z se añade al conjunto W
			coste[z] = Infinito;
			
			// debido a la incorporación de z,
			// se ajusta coste[] para el resto de vértices

			for (int j = 1; j < n; j++)
				if ((Pesos[z][j] < coste[j]) && !W[j])
				{
				coste[j] = Pesos[z][j];
				masCerca[j] = z;
			}			
		}				
		return longMin;
	}
}
