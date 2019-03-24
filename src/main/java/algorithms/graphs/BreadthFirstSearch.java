package algorithms.graphs;

/*
 * Algorithm:
 * -----------
 * 
 * BFS(Graph G, StartVertex S):
 *    [All nodes initially unexplored]
 *    Mark S as explored
 *    Let Q = Queue initialized with S
 *    while Q is not empty:
 *        V = pop the element from Q
 *        For each edges (V, W):
 *            if W is unexplored:
 *                Mark W as explored
 *                Add W to Q
 *
 * Time Complexity: O(V+E)
 */

import java.io.*;
import java.util.*;

public class BreadthFirstSearch {
	
	public void search(Map<Integer, ArrayList<Integer>> graph, int startNode) {
		ArrayList<Integer> explored = new ArrayList<Integer>();
		explored.add(startNode);
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(startNode);
		while(!Q.isEmpty()) {
			int V = Q.poll();
			ArrayList<Integer> edges = graph.get(V);
			for(int edge : edges) {
				if(!explored.contains(edge)) {
					explored.add(edge);
					Q.add(edge);
				}
			}
		}
		System.out.println("Explored Order: ");
		for(int node:explored) {
			System.out.print(" " + node + ",");
		}
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("/home/ragesh/eclipseworkspace/Algorithms-Java/resources/graph");
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String line;
		Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
		while((line = bReader.readLine()) != null)
		{
			ArrayList<Integer> edges = new ArrayList<Integer>();
			String[] val = line.split("\t");
			int node = 0;
			for(int i=0; i<val.length; i++) {
				if(i==0) {
					node = Integer.parseInt(val[0]);
					continue;
				}
				edges.add(Integer.parseInt(val[i]));
			}
			graph.put(node, edges);
		}
		
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		bfs.search(graph, 8);

	}

}
