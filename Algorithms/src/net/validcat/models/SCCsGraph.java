package net.validcat.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.validcat.utils.Utils;

public class SCCsGraph {
	private List<Vertex> vertexes = new ArrayList<Vertex>();

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

	public static SCCsGraph build(String path, long size) {
		System.out.println("Start building Graph");
		SCCsGraph graph = new SCCsGraph();
		//Add vertexes
		for (int i = 1; i <= size; i++) {
			Vertex vertex = new Vertex(i);
			graph.addVertex(vertex);
		}
		System.out.println("Vertexes were added");
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				int[] row = Utils.convertStringArraytoIntArray(line.split("\\s+")); //Integer.parseInt(line);
				graph.getVertexes().get(row[0]-1).addVertexAsEdge(graph.getVertexes().get(row[1]-1));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Graph was done");
		
		trimGraphSize(graph);
		
		return graph;
	}
	
	public static SCCsGraph buildReverse(String path, long size) {
		System.out.println("Start building reverse Graph");
		SCCsGraph graph = new SCCsGraph();
		//Add vertexes
		for (int i = 1; i <= size; i++) {
			Vertex vertex = new Vertex(i);
			graph.addVertex(vertex);
		}
		System.out.println("Vertexes were added");
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				int[] row = Utils.convertStringArraytoIntArray(line.split("\\s+")); //Integer.parseInt(line);
				graph.getVertexes().get(row[1]-1).addVertexAsEdge(graph.getVertexes().get(row[0]-1));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Graph was done");
		((ArrayList<Vertex>)graph.getVertexes()).trimToSize();
		return graph;
	}

	public SCCsGraph reverseOrder() {
		SCCsGraph reversGraph = new SCCsGraph();
		for (Vertex v : vertexes) {
			reversGraph.addVertex(new Vertex(v.getIndex()));
		}
		
		for (Vertex v : vertexes) {
			for (Vertex vertexB : v.getVertexesAsEdges()) {
				Vertex vertexRA = reversGraph.findVertexByIndex(vertexB.getIndex());
				vertexRA.addVertexAsEdge(reversGraph.findVertexByIndex(v.getIndex()));
			}
		}
		return reversGraph;
	}
	
	private static void trimGraphSize(SCCsGraph graph) {
		for (Vertex vertex : graph.getVertexes()) {
			vertex.trimVertexSize();
		}
		((ArrayList<Vertex>)graph.getVertexes()).trimToSize();
	}
	
	public Vertex findVertexByIndex(int i) {
		for (Vertex v : vertexes) {
			if (v.getIndex() == i) return v;
		}
		return null;
	}

	public static void printGraph(SCCsGraph g) {
		System.out.println("Graph");
		for (Vertex vertex : g.getVertexes()) {
			vertex.printVertex();
		}
		
	}

	public static void main(String[] args) {
		SCCsGraph g = build("minGraph.txt", 10);
		printGraph(g);
		SCCsGraph rG = g.reverseOrder();
		printGraph(rG);
	}

}
