package c_connection;

import image_analysis.SampleImage;

public class Node {
	private SampleImage value;
	
	private Node arco;
	private int peso;
	
	private Node Hijo_Izq;
	private Node Hijo_Der;
	
	public SampleImage getValue() {
		return value;
	}

	public void setValue(SampleImage value) {
		this.value = value;
	}

	public Node getArco() {
		return arco;
	}

	public void setArco(Node arco) {
		this.arco = arco;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Node getHijo_Izq() {
		return Hijo_Izq;
	}

	public void setHijo_Izq(Node hijo_Izq) {
		Hijo_Izq = hijo_Izq;
	}

	public Node getHijo_Der() {
		return Hijo_Der;
	}

	public void setHijo_Der(Node hijo_Der) {
		Hijo_Der = hijo_Der;
	}

	public Node(SampleImage pValue, int pPeso) {
		value = pValue;
		peso = pPeso;
	}

}
