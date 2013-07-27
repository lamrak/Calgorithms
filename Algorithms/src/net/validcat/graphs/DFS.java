package net.validcat.graphs;

import net.validcat.models.Graph;
import net.validcat.models.Vertex;

public class DFS {
	private Vertex s;
	private int label = 0;
	private boolean topological;
	
	public void dfs(Graph g, Vertex vertex) {
		vertex.setExplored(true);
		vertex.setLeader(s);
		for (Vertex vertexB : vertex.getEdges()) {
			if (!vertexB.isExplored()) dfs(g, vertexB);
		}
		
		if (topological) vertex.setLabel(label--); // for topological counting
		else vertex.setLabel(++label); //for SCC
	}
	
	public void dfsTopologic(Graph g) {
		label = g.getVertexes().size();
		
		for (Vertex vertex : g.getVertexes()) {
			if (!vertex.isExplored()) dfs(g, vertex);
		}
	}
	
	public void dfsLoop(Graph g) {
		for (Vertex vertex : g.getVertexes()) {
			vertex.setExplored(false);
		}
		for (int i = g.getVertexes().size(); i > 0; i--) {
			s = g.findVertexByIndex(i);
			if (!s.isExplored()) {
				dfs(g, s);
			}
		}
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
