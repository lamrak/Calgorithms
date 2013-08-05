package net.validcat.models;

public class Edge implements Comparable<Edge> {
	private Vertex vertexA;
	private Vertex vertexB;
	private int cost;
	
	public Edge(Vertex vertexA, Vertex vertexB, int cost) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Vertex getVertexA() {
		return vertexA;
	}

	public Vertex getVertexB() {
		return vertexB;
	}
	
	@Override
	public String toString() {
		return "[Edge: from: " + vertexA.getIndex() + " to: " + vertexB.getIndex() + "]";
	}

	@Override
	public int compareTo(Edge o) {
		return getCost() + getVertexA().getDistance() > o.getCost() + o.getVertexA().getDistance() ? 1 
				: getCost() + getVertexA().getDistance() < o.getCost() + o.getVertexA().getDistance() ? -1 : 0; 
	}
	
}