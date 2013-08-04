package net.validcat.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DijkstraGraph {
	private Vertex[] vertexes;
	private List<Edge> edges = new ArrayList<Edge>();
	
	public DijkstraGraph(int vertexNum) {
		vertexes = new Vertex[vertexNum]; 
	}
	
	public Vertex[] getVertexes() {
		return vertexes;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public void addVertex(Vertex vertex) {
		vertexes[vertex.getIndex()-1] = vertex;
	}
	
	public void addEdges(Edge edge) {
		findVertexBiIndex(edge.getVertexA().getIndex()).addEdge(edge);
		edges.add(edge);
	}
	
	public Vertex findVertexBiIndex(int i) {
		for (Vertex vertex : vertexes) {
			if (vertex.getIndex() == i) return vertex;
		}
		return null;
	}
	
	public static DijkstraGraph build(String path, int numOfVertexes) throws IOException {
		DijkstraGraph g = new DijkstraGraph(numOfVertexes);
		
		for (int i = 1; i <= numOfVertexes; i++) {
			g.addVertex(new Vertex(i));
		}
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while ((line = br.readLine()) != null) {
			String[] rowStr = line.split("\\s+");
			for (int i = 1; i < rowStr.length; i++) {
				String[] split = (rowStr[i]).split(",");
				Edge edge = new Edge(g.findVertexBiIndex(Integer.parseInt(rowStr[0])), g.findVertexBiIndex(Integer.parseInt(split[0])), Integer.parseInt(split[1]));
				g.addEdges(edge);
			}
		}
		br.close();
		
		return g;
	}
	
}
