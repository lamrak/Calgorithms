package net.validcat.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
	private static int[] distTo;
	private List<Edge> stack = new ArrayList<Edge>();
	
	public static void main(String[] args) throws IOException {
		String path = "dijkstraData.txt";
		int numOfVertexes = 200;
		
		Dijkstra dijkstra = new Dijkstra();
		Graph g = dijkstra.init(path, numOfVertexes);
		
		dijkstra.shortestPath(g, 1);
		
		for (int i = 1; i <= numOfVertexes; i++) {
			System.out.println("To: " + i + " length: " + distTo[i-1]);	
		}
	}
	
	private Graph init(String path, int numOfVertexes) throws IOException {
		Graph g = new Graph(numOfVertexes);
		
		distTo = new int[numOfVertexes];
		
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
	
	private void shortestPath(Graph g, int s) {
		Vertex start = g.findVertexBiIndex(s);
		// set S as explored
		start.setExplored(true);
		start.setDistance(0);
		distTo[start.getIndex()-1] = start.getDistance();

		//Add Edges into stack
		addBoundEdgesStack(start.getEdges());

		while (!stack.isEmpty()) {
			// Compare to Edges for minimum
			Edge minEdge = findMinEdge();
			Vertex vertexB = minEdge.getVertexB();
			// add new Vertex in X
			minEdge.getVertexB().setExplored(true);
			// set to new Vertex distance value
			vertexB.setDistance(minEdge.getVertexA().getDistance() + minEdge.getCost());
			distTo[vertexB.getIndex()-1] = vertexB.getDistance();
		
			// add new Edges into Stack
			addBoundEdgesStack(vertexB.getEdges());
			
			// delete all new vertexe's in-edges from stack	
			clearStack(minEdge);
		}
		
		// while stack not empty
	}

	private void clearStack(Edge minEdge) {
		List<Edge> temp = new ArrayList<Edge>();
		for (Edge edge : stack) {
			if (edge.getVertexB() == minEdge.getVertexB()) {
				temp.add(edge);
			}
			if (edge.getVertexB() == minEdge.getVertexA() && edge.getVertexA() == minEdge.getVertexB()) {
				temp.add(edge);
			}
		}
		stack.removeAll(temp);
	}

	private Edge findMinEdge() {
		Edge minCostEdge = stack.get(0);
		int cost = minCostEdge.getCost() + minCostEdge.getVertexA().getDistance();
		for (int i = 1; i < stack.size(); i++) { // assume that first (0) is min
			Edge edge = stack.get(i);
			if ((edge.getCost() + edge.getVertexA().getDistance()) < cost && !edge.getVertexB().isExplored()) {
				minCostEdge = stack.get(i);
				cost = minCostEdge.getCost() + minCostEdge.getVertexA().getDistance();
			}
		}
		
		return minCostEdge;
	}

	private void addBoundEdgesStack(List<Edge> edges) {
		for (Edge edge : edges) {
			if (!edge.getVertexB().isExplored()) stack.add(edge);
		}
	}

}

class Graph {
	private Vertex[] vertexes;
	private List<Edge> edges = new ArrayList<Edge>();
	
	public Graph(int vertexNum) {
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
	
}

class Vertex {
	private int index;
	private int distance;
	private boolean isExplored;
	private List<Edge> edges = new ArrayList<Edge>();

	public Vertex(int index) {
		this.index = index;
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

	public int getIndex() {
		return index;
	}
	
	public boolean isExplored() {
		return isExplored;
	}

	public void setExplored(boolean isExplored) {
		this.isExplored = isExplored;
	}

	@Override
	public String toString() {
		return "[Vertex name=" + index + "]";
	}
}

class Edge {
	private Vertex vertexA;
	private Vertex vertexB;
	private int cost;
	
	public Edge(Vertex vertexA, Vertex vertexB) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
	}
	
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
	
}