package net.validcat.graphs;

import java.io.IOException;
import java.util.List;

import net.validcat.models.DijkstraGraph;
import net.validcat.models.Edge;
import net.validcat.models.Vertex;
import net.validcat.structure.IBinaryHeap;
import net.validcat.structure.HeapMin;

public class DijkstraHeap {
	private static int[] distTo;
	private IBinaryHeap<Edge> heap = new HeapMin<Edge>();
	
	public static void main(String[] args) throws IOException {
		String path = "dijkstraData.txt";
		int numOfVertexes = 200;
		
		long startTime = System.currentTimeMillis();
		DijkstraHeap dijkstra = new DijkstraHeap();
		DijkstraGraph g = dijkstra.init(path, numOfVertexes);
		dijkstra.shortestPath(g, g.findVertexBiIndex(1));
		long endTime = System.currentTimeMillis();
		for (int i = 1; i <= numOfVertexes; i++) {
			System.out.println("To: " + i + " length: " + distTo[i-1]);	
		}
		System.out.println(endTime - startTime);
	}
	
	private DijkstraGraph init(String path, int numOfVertexes) throws IOException {
		distTo = new int[numOfVertexes];
		return DijkstraGraph.build(path, numOfVertexes);
	}

	private void shortestPath(DijkstraGraph g, Vertex start) {
		handleVertex(start, 0);
		while (!heap.isEmpty()) { // while stack not empty
			// Compare to Edges for minimum, deleted explored nodes
			Edge minEdge = getMinEdge();
			if (minEdge == null) return; //heap is empty
			Vertex vertexB = minEdge.getVertexB();
			handleVertex(vertexB, minEdge.getVertexA().getDistance() + minEdge.getCost());
		}
	}

	private Edge getMinEdge() {
		Edge minEdge = (Edge) heap.extract();
		while (minEdge != null && minEdge.getVertexB().isExplored()) {
			minEdge = (Edge) heap.extract();
		}
		return minEdge;
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
		addBoundEdges(vertex.getEdges());
	}

	private void addBoundEdges(List<Edge> edges) {
		for (Edge edge : edges) {
			if (!edge.getVertexB().isExplored()) heap.insert(edge);
		}
	}

}


