package net.validcat.graphs;

import java.util.HashMap;
import java.util.Map;

import net.validcat.models.Graph;
import net.validcat.models.Vertex;

public class StrongComponents {

	public static void main(String[] args) {
		Graph g = Graph.build("D:\\android\\Git\\algorithmes\\Algorithms\\SCC.txt");
		Graph reversedGraph = g.reverseOrder(); //Graph.buildReverse("D:\\android\\Git\\algorithmes\\Algorithms\\minGraph.txt"); //

		DFS dfs = new DFS();
		
		dfs.dfsLoop(reversedGraph);
		for (Vertex v : g.getVertexes()) {
			int newVertexIndex = reversedGraph.findVertexByIndex(v.getIndex()).getLabel();
			v.setIndex(newVertexIndex);
		}
		
		dfs.dfsLoop(g);
		
		//get result
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		System.out.println("Map is created");
		for (Vertex v : g.getVertexes()) {
			int i = 0;
			if (resultMap.get(v.getLeader().getIndex()) != null) {
				i = resultMap.get(v.getLeader().getIndex());
			}
			resultMap.put(v.getLeader().getIndex(), ++i);
//			System.out.println(v.toString() + " in " + v.getLeader());
		}
		for (Integer key : resultMap.keySet()) {
			System.out.println(key + ": " + resultMap.get(key));
		}
	}
	
}
