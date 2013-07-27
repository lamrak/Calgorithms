package net.validcat.graphs;

import net.validcat.models.Graph;
import net.validcat.models.Vertex;

public class StrongComponents {

	public static void main(String[] args) {
		Graph g = Graph.build("D:\\SVN\\Git\\calgorithms\\Algorithms\\minGraph.txt");
		Graph reversedGraph = g.reverseOrder();

		DFS dfs = new DFS();
		
		dfs.dfsLoop(reversedGraph);
		for (Vertex v : g.getVertexes()) {
			int newVertexIndex = reversedGraph.findVertexByIndex(v.getIndex()).getLabel();
			v.setIndex(newVertexIndex);
		}
		
		dfs.dfsLoop(g);
		
		//get result
		for (Vertex v : g.getVertexes()) {
			System.out.println(v.toString() + " in " + v.getLeader());
		}
	}
	
}
