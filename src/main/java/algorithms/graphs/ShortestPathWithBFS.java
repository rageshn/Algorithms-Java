package algorithms.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * Algorithm:
 * -----------
 * 
 * BFS(Graph G, StartVertex S):
 *    [All nodes initially unexplored]
 *    Initiate dist(V) = 0, if V == S
 *             dist(V) = INFINITY, if V != S
 *    Mark S as explored
 *    Let Q = Queue initialized with S
 *    while Q is not empty:
 *        V = pop the element from Q
 *        For each edges (V, W):
 *            if W is unexplored:
 *            	  Set dist(W) = dist(V) + 1
 *                Mark W as explored
 *                Add W to Q
 *
 * Time Complexity: O(V+E)
 */

public class ShortestPathWithBFS {
	
	public Map<Integer, Integer> distances = new HashMap<Integer, Integer>();
	
	public void search(Map<Integer, ArrayList<Integer>> graph, int startNode) {
		ArrayList<Integer> explored = new ArrayList<Integer>();
		explored.add(startNode);
		distances.put(startNode, 0);
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(startNode);
		while(!Q.isEmpty()) {
			int V = Q.poll();
			ArrayList<Integer> edges = graph.get(V);
			for(int edge : edges) {
				if(!explored.contains(edge)) {
					int nodeDist = distances.get(V) + 1;
					distances.put(edge, nodeDist);
					explored.add(edge);
					Q.add(edge);
				}
			}
		}
		System.out.println("Explored Order: ");
		for(int node:explored) {
			System.out.print(" " + node + ",");
		}
		
		System.out.println("\n Distances: ");
		for(Map.Entry<Integer, Integer> dist : distances.entrySet()) {
			System.out.print("(" + dist.getKey() + ", " + dist.getValue() + "),"); 
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
		
		ShortestPathWithBFS bfs = new ShortestPathWithBFS();
		bfs.search(graph, 8);

	}
}
