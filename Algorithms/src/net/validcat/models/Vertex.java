package net.validcat.models;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private int index;
	private List<Vertex> listVertexesB = new ArrayList<Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();
	
	private boolean explored = false;
	private int label = 0;
	private int distance;
	
	private Vertex leader; //for SCCs 
	
	public Vertex(int index) {
		this.index = index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	
	public List<Vertex> getVertexesAsEdges() {
		return listVertexesB;
	}

	public void setVertexesAsEdges(List<Vertex> edges) {
		this.listVertexesB = edges;
	}
	
	public void addVertexAsEdge(Vertex vertexB) {
		listVertexesB.add(vertexB);
	}
	
	public void removeVertexAsEdge(Vertex vertexB) {
		listVertexesB.remove(vertexB);
	}
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public Vertex getLeader() {
		return leader;
	}

	public void setLeader(Vertex leader) {
		this.leader = leader;
	}
	
	public void printVertex() {
		System.out.println(toString() + ": " + index);
		for (Vertex vertexB : listVertexesB) {
			System.out.println("Connected: " + vertexB.getIndex());
		}
	}

	@Override
	public String toString() {
		return "Vertex [name=" + index + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (index != other.index)
			return false;
		return true;
	}

	public void trimVertexSize() {
		((ArrayList<Vertex>)listVertexesB).trimToSize();
	}
	
}
