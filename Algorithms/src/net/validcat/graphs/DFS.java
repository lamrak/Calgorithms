package net.validcat.graphs;

import net.validcat.models.Graph;
import net.validcat.models.Vertex;

public class DFS {
	private Vertex s;
	private int label = 0;
	private boolean topological;
	
	public void dfs(Graph g, Vertex vertex) {
		try {
			System.out.println("DFS: " + vertex);	
		} catch (StackOverflowError e) {
			System.out.println(label);
		}
		
		vertex.setExplored(true);
		vertex.setLeader(s);
		for (Vertex vertexB : vertex.getVertexesAsEdges()) {
			if (!vertexB.isExplored()) dfs(g, vertexB);
		}
		
		vertex.setLabel(++label); //for SCC
	}
	
	public void dfsLoop(Graph g) {
		System.out.println("Start DFS loop");
		for (Vertex vertex : g.getVertexes()) {
			vertex.setExplored(false);
		}
		System.out.println("All vertexes enexplored");
		for (int i = g.getVertexes().size(); i > 0; i--) {
			s = g.findVertexByIndex(i);
			if (!s.isExplored()) {
				dfs(g, s);
			}
		}
		System.out.println("DFS loop done");
	}
	
	public int getLabel() {
		return label;
	}


	public void setLabel(int label) {
		this.label = label;
	}


	public boolean isTopological() {
		return topological;
	}


	public void setTopological(boolean topological) {
		this.topological = topological;
	}


	public static void main(String[] args) {
		//TODO test algorith here
	}
	
}
