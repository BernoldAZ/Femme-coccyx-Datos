package build;

import c_connection.Node;
import image_analysis.SampleImage;

public class Vertex {
	Arc[] arcs = new Arc[3]; //Tiene hijo izquierdo[0], hijo derecho[1] y con monticulo[2]
	private SampleImage value;
	
	public Vertex (Node pInformation) {
		value = pInformation.getValue();
		Node nodeHeap = pInformation.getArco();
		if (nodeHeap != null) {
			Vertex vertexHeap = new Vertex(nodeHeap);
			Arc arcHeap = new Arc(this, vertexHeap, pInformation.getPeso());
			arcs[2] = arcHeap;
		}
	}
	
	public void setArcIzq(Vertex pVertexIzq) {
		Arc arcIzq = new Arc(this, pVertexIzq);
		arcs[0] = arcIzq;
	}

	public void setArcDer(Vertex pVertexDer) {
		Arc arcDer = new Arc(this, pVertexDer);
		arcs[1] = arcDer;
	}
}
