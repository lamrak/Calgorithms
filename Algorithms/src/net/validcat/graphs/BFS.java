package net.validcat.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import net.validcat.models.Graph;
import net.validcat.models.Vertex;
import net.validcat.utils.Utils;

public class BFS {
	private Queue<Vertex> queue;
	
	public static void main(String[] args) {
		BFS bfsAlg = new BFS();
		Graph graph = Graph.build("", 0);
		
		bfsAlg.bfs(graph, graph.getVertexes().get(0));
	}
	
	public int countgraphConnectivity(Graph graph) {
		int partitions = 0;
		for (Vertex vertex : graph.getVertexes()) {
			if (!vertex.isExplored()) {
				bfs(graph, vertex);
				partitions++;
			}
		}
		
		return partitions;
	}

	public void bfs(Graph graph, Vertex startPoint) {
		if (queue == null) {
			queue = new LinkedList<Vertex>();
			queue.offer(startPoint);
		}
		while (queue.size() > 0) {
			Vertex vertex = queue.poll();
			vertex.setExplored(true);
			List<Vertex> edges = vertex.getVertexesAsEdges();
			for (Vertex vertexB : edges) {
				if (!vertexB.isExplored()) {
					vertexB.setExplored(true);
					vertexB.setLabel(vertex.getLabel()+1);
					queue.offer(vertexB);
				}
			}
		}
	}

}
