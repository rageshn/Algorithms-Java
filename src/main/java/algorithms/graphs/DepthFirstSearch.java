package algorithms.graphs;

/*
 * Algorithm (Recursive):
 * -----------------------
 * 
 * DFS(Graph G, StartVertex S):
 *    [All nodes initially unexplored]
 *    Mark S as explored
 *    For every edge (S,V):
 *        if V is unexplored:
 *            DFS(G,V)
 * 
 * 
 * 
 * Algorithm (Non-Recursive):
 * ---------------------------
 * 
 * DFS(Graph G, startVertex S):
 *    [All nodes initially unexplored]
 *    Mark S as explored
 *    Let St = Stack initialized with S
 *    while St is not empty:
 *        V = pop the element from St
 *        For each edges (V, W):
 *            if W is unexplored:
 *                Mark W as explored
 *                Add W to St
 *
 *
 * Time Complexity: O(V+E) --> O(# of nodes reachable from S + # of edges reachable from S)
 *
 * => Used in maze problems
 * 
 */

import java.io.*;
import  java.util.*;

public class DepthFirstSearch {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("/home/ragesh/eclipseworkspace/Algorithms-Java/resources/graph");
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String line;
		Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
		while((line = bReader.readLine()) != null) {
			ArrayList<Integer> edges = new ArrayList<Integer>();
			String[] val = line.split("\t");
			int node = 0;
			for(int i = 0; i<val.length; i++) {
				if(i ==0) {
					node = Integer.parseInt(val[i]);
					continue;
				}
				edges.add(Integer.parseInt(val[i]));
			}
			graph.put(node, edges);
		}
		bReader.close();
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		dfs.dfsNonRecursive(graph, 8);
		ArrayList<Integer> explored = dfs.dfsRecursive(graph, 8, new ArrayList<Integer>());
		
		System.out.println("\nExplored Order (Recursive): ");
		for(int node:explored) {
			System.out.print(" " + node + ",");
		}
	}
	
	public void dfsNonRecursive(Map<Integer, ArrayList<Integer>> graph, int startNode) {
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> explored = new ArrayList<Integer>();
		explored.add(startNode);
		stack.push(startNode);
		while(!stack.isEmpty()) {
			int V = stack.pop();
			ArrayList<Integer> edges = graph.get(V);
			for(int edge : edges) {
				if(!explored.contains(edge)) {
					explored.add(edge);
					stack.push(edge);
				}
			}
		}
		System.out.println("Explored Order (Non-Recursive): ");
		for(int node:explored) {
			System.out.print(" " + node + ",");
		}
	}
	
	public ArrayList<Integer> dfsRecursive(Map<Integer,ArrayList<Integer>> graph, int startNode, ArrayList<Integer> explored) {
		explored.add(startNode);
		for(int edge : graph.get(startNode)) {
			if(!explored.contains(edge)) {
				dfsRecursive(graph, edge, explored);
			}
		}
		return explored;
	}

}
