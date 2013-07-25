package net.validcat.bfs;

import java.io.IOException;
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
		Graph graph = bfsAlg.buildGraph("");
		
		bfsAlg.bfs(graph, graph.getVertexes().get(0));
	}
	
	public Graph buildGraph(String path) {
		Graph graph = new Graph();
		List<int[]> list = null;
		try {
			list = Utils.readDataFromFileAsList(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < list.size(); i++) {
			int[] v = list.get(i);
			Vertex vertex = new Vertex(String.valueOf(v[0]), v[0]);
			for (int j : v) {
				vertex.addEdge(new Vertex(String.valueOf(j), j));
			}
			graph.addVertexe(vertex);
		}

		return graph;
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
			List<Vertex> edges = vertex.getEdges();
			for (Vertex vertexB : edges) {
				if (!vertexB.isExplored()) {
					vertexB.setExplored(true);
					vertexB.setDistance(vertex.getDistance()+1);
					queue.offer(vertexB);
				}
			}
		}
	}

}
