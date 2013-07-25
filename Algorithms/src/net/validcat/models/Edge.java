package net.validcat.models;


public class Edge {
	private Vertex vertexA;
	private Vertex vertexB;
	private boolean direction = false;
	
	public Edge(Vertex vertexA, Vertex vertexB) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
	}
	
	/**
	 * Constructor for directed graph
	 * @param vertexA
	 * @param vertexB
	 * @param direction
	 */
	public Edge(Vertex vertexA, Vertex vertexB, boolean direction) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.direction = direction;
	}

	public Vertex getVertexA() {
		return vertexA;
	}

	public Vertex getVertexB() {
		return vertexB;
	}
	
	public boolean isLoop(){
		return vertexA.equals(vertexB);
	}
	
	public boolean isDirect(){
		return direction;
	}
	
}
