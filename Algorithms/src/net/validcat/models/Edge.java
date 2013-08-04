package net.validcat.models;

public class Edge implements IHeapObject {
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
	public int getKey() {
		return cost + vertexA.getDistance();
	}
	
}