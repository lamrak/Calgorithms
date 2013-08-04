package net.validcat.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.validcat.utils.Utils;

public class GraphSCCs {
	private List<Vertex> vertexes;
//	private List<Edge> edges;
	
	public GraphSCCs() {
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

	public static GraphSCCs build(String path, long size) {
		System.out.println("Start building Graph");
		GraphSCCs graph = new GraphSCCs();
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
//				list.add(row);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Graph was done");
		
		trimGraphSize(graph);
		
		return graph;
	}
	
	private static void trimGraphSize(GraphSCCs graph) {
		for (Vertex vertex : graph.getVertexes()) {
			vertex.trimVertexSize();
		}
		((ArrayList<Vertex>)graph.getVertexes()).trimToSize();
	}

	public static GraphSCCs buildReverse(String path, long size) {
		System.out.println("Start building reverse Graph");
		GraphSCCs graph = new GraphSCCs();
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
//				list.add(row);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Graph was done");
		((ArrayList<Vertex>)graph.getVertexes()).trimToSize();
		return graph;
	}

	public GraphSCCs reverseOrder() {
		GraphSCCs reversGraph = new GraphSCCs();
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
	
	public Vertex findVertexByIndex(int i) {
		for (Vertex v : vertexes) {
			if (v.getIndex() == i) return v;
		}
		return null;
	}

	public static void printGraph(GraphSCCs g) {
		System.out.println("Graph");
		for (Vertex vertex : g.getVertexes()) {
			vertex.printVertex();
		}
		
	}

//	private boolean contains(Vertex vertex) {
//		return vertexes.contains(vertex);
//	}
//	
	public static void main(String[] args) {
		GraphSCCs g = build("D:\\SVN\\Git\\calgorithms\\Algorithms\\minGraph.txt", 10);
		printGraph(g);
		GraphSCCs rG = g.reverseOrder();
		printGraph(rG);
	}

}
