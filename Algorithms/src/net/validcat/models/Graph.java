package net.validcat.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Finishings;

import net.validcat.utils.Utils;


public class Graph {
	private List<Vertex> vertexes;
//	private List<Edge> edges;
	
	public Graph() {
		vertexes = new ArrayList<Vertex>();
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public void setVertexes(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
	
	public void addVertex(Vertex vertex) {
		vertexes.add(vertex);
	}
	
	public void removeVertexe(Vertex vertex) {
		vertexes.remove(vertex);
	}

//	public List<Edge> getEdges() {
//		return edges;
//	}
//
//	public void setEdges(List<Edge> edges) {
//		this.edges = edges;
//	}
	
//	public void addEdge(Edge edge) {
//		edges.add(edge);
//	}
//	
//	public void removeEdge(Edge edge) {
//		edges.remove(edge);
//	}
	
	public static Graph build(String path) {
		Graph graph = new Graph();
		List<int[]> list = null;
		try {
			list = Utils.readDataFromFileAsList(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Add vertexes
		for (int i = 0; i < list.size(); i++) {
			int[] v = list.get(i);
			Vertex vertex = new Vertex(String.valueOf(v[0]), v[0]);
			graph.addVertex(vertex);
		}
		//Add edges (as a list of vartixBs in the vertixA)
		for (int i = 0; i < list.size(); i++) {
			int[] endpointArr = list.get(i);
			for (int j = 1; j < endpointArr.length; j++) {
				graph.findVertexByIndex(endpointArr[0]).addEdge(graph.findVertexByIndex(endpointArr[j]));	
			}
			
		}
		

		return graph;
	}

	public Graph reverseOrder() {
		Graph reversGraph = new Graph();
		for (Vertex v : vertexes) {
			reversGraph.addVertex(new Vertex(v.getIndex(), v.getIndex()));
		}
		
		for (Vertex v : vertexes) {
			for (Vertex vertexB : v.getEdges()) {
				Vertex vertexRA = reversGraph.findVertexByIndex(vertexB.getIndex());
				vertexRA.addEdge(reversGraph.findVertexByIndex(v.getIndex()));
			}
		}
		return reversGraph;
	}
	
	public Vertex findVertexByIndex(int i) {
		for (Vertex v : vertexes) {
			if (v.getIndex() == i) return v;
		}
		return null;
	}

	public static void printGraph(Graph g) {
		System.out.println("Graph");
		for (Vertex vertex : g.getVertexes()) {
			vertex.printVertex();
		}
		
	}

	private boolean contains(Vertex vertex) {
		return vertexes.contains(vertex);
	}
	
	public static void main(String[] args) {
		Graph g = build("D:\\SVN\\Git\\calgorithms\\Algorithms\\minGraph.txt");
		printGraph(g);
		Graph rG = g.reverseOrder();
		printGraph(rG);
	}

}
