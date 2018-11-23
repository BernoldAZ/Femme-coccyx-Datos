package build;

public class Arc {
	
	private Vertex inicialVertex;
	private Vertex finalVertex;
	private int peso;
	
	public Vertex getInicialVertex() {
		return inicialVertex;
	}

	public Vertex getFinalVertex() {
		return finalVertex;
	}

	public int getPeso() {
		return peso;
	}

	public Arc( Vertex pInicialVertex, Vertex pFinalVertex) {
		inicialVertex = pInicialVertex;
		finalVertex = pFinalVertex;
		peso = 1;
	}
	
	public Arc( Vertex pInicialVertex, Vertex pFinalVertex, int pPeso) {
		inicialVertex = pInicialVertex;
		finalVertex = pFinalVertex;
		peso = pPeso;
	}

}
