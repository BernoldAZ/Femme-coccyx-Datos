package c_connection;

import image_analysis.SampleImage;

public class Node {
	public SampleImage value;
	
	public Node arco;
	public int peso;
	
	public Node Hijo_Izq;
	public Node Hijo_Der;
	
	public Node(SampleImage pValue, int pPeso) {
		value = pValue;
		peso = pPeso;
	}

}
