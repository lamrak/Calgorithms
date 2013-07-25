package net.validcat.models;

import java.util.List;

public class Vertex {
	private String name;
	private int index;
	private List<Vertex> listVertexesB;
	private boolean explored = false;
	private int distance = 0;
	
	public Vertex(String name, int index) {
		super();
		this.name = name;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	
	public List<Vertex> getEdges() {
		return listVertexesB;
	}

	public void setEdges(List<Vertex> edges) {
		this.listVertexesB = edges;
	}
	
	public void addEdge(Vertex vertexB) {
		listVertexesB.add(vertexB);
	}
	
	public void removeEdge(Vertex vertexB) {
		listVertexesB.remove(vertexB);
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Vertex [name=" + name + "]";
	}
	
}
