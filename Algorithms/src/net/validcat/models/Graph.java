package net.validcat.models;

import java.util.List;


public class Graph {
	private List<Vertex> vertexes;
	private List<Edge> edges;
	
	public Graph() {
		
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public void setVertexes(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
	
	public void addVertexe(Vertex vertex) {
		vertexes.add(vertex);
	}
	
	public void removeVertexe(Vertex vertex) {
		vertexes.remove(vertex);
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}

}
