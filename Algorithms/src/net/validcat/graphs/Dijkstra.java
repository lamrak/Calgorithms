package net.validcat.graphs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.validcat.models.DijkstraGraph;
import net.validcat.models.Edge;
import net.validcat.models.Vertex;

public class Dijkstra {
	private static int[] distTo;
	private List<Edge> stack = new ArrayList<Edge>();
	
	public static void main(String[] args) throws IOException {
		String path = "dijkstraData.txt";
		int numOfVertexes = 200;
		
		Dijkstra dijkstra = new Dijkstra();
		DijkstraGraph g = dijkstra.init(path, numOfVertexes);
		dijkstra.shortestPath(g, g.findVertexBiIndex(1));
		
		for (int i = 1; i <= numOfVertexes; i++) {
			System.out.println("To: " + i + " length: " + distTo[i-1]);	
		}
	}
	
	private DijkstraGraph init(String path, int numOfVertexes) throws IOException {
		distTo = new int[numOfVertexes];
		return DijkstraGraph.build(path, numOfVertexes);
	}

	private void shortestPath(DijkstraGraph g, Vertex start) {
		handleVertex(start, 0);
		while (!stack.isEmpty()) { // while stack not empty
			// Compare to Edges for minimum
			Edge minEdge = findMinEdge();
			Vertex vertexB = minEdge.getVertexB();
			handleVertex(vertexB, minEdge.getVertexA().getDistance() + minEdge.getCost());
			// delete all new vertexe's in-edges from stack	
			clearStack(minEdge);
		}
	}

	/**
	 * Add new Vertex in X and set distance value. Add new Edges into Stack
	 * @param vertex
	 * @param distance
	 */
	private void handleVertex(Vertex vertex, int distance) {
		vertex.setExplored(true);
		vertex.setDistance(distance);
		distTo[vertex.getIndex()-1] = vertex.getDistance();
		addBoundEdgesStack(vertex.getEdges());
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


