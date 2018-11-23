package c_connection;

public class Grafo {
	private Node raiz;
	private Node nodeMaxRepetitions;

	public Node getRaiz() {
		return raiz;
	}

	public Node getNodeMaxRepetitions() {
		return nodeMaxRepetitions;
	}

	public Grafo(Node pRaiz) {
		raiz = pRaiz;
		nodeMaxRepetitions = pRaiz;
		searchMaxRepetitions(raiz);
	}

	private void searchMaxRepetitions(Node pRoad) {
		if (pRoad != null) {
			if (pRoad.getValue().getRepetitions() < nodeMaxRepetitions.getValue().getRepetitions()) { //No cambia nada
				searchMaxRepetitions(raiz.getHijo_Izq());
				searchMaxRepetitions(raiz.getHijo_Der());
			}
			else {
				nodeMaxRepetitions = pRoad;
				searchMaxRepetitions(raiz.getHijo_Izq());
				searchMaxRepetitions(raiz.getHijo_Der());
			}
			
		}
		else {
			return;
		}
	}
	
}
