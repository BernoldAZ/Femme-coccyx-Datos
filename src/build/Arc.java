package build;

import c_connection.Node;

public class Arc {
	
	private Node inicialVertex;
	private Node finalVertex;
	private int peso;
	
	public Node getInicialVertex() {
		return inicialVertex;
	}

	public Node getFinalVertex() {
		return finalVertex;
	}

	public int getPeso() {
		return peso;
	}

	public Arc( Node pInicialVertex, Node pFinalVertex) {
		inicialVertex = pInicialVertex;
		finalVertex = pFinalVertex;
		peso = 1;
	}
	
	public Arc( Node pInicialVertex, Node pFinalVertex, int pPeso) {
		inicialVertex = pInicialVertex;
		finalVertex = pFinalVertex;
		peso = pPeso;
	}

}
