package algorithms.graphs;

/*
 * 
 * Algorithm:
 * -----------
 * 
 * DFS(Graph G, startNode S):
 *    Mark S as explored
 *    For every edge (S, V):
 *        if V is unexplored:
 *            DFS(G, V)
 *    Add S to Stack
 *
 *
 * DFS-Loop(Graph G):
 *    [All nodes initially unexplored]
 *    Initiate Stack as empty [To keep track of ordering]
 *    For each vertex V:
 *        if V is unexplored:
 *            DFS(G,V)
 *    return Stack
 *
 *
 * Time Complexity: O(V+E)
 * Condition: The graph should be a DAG. If there are any cycles, then there wont be any topological ordering.
 * 
 */


import java.io.*;
import java.util.*;

public class TopologicalSort {
	
	public static void depthFirstSearch(Map<String, ArrayList<String>> graph, String startNode, Stack<String> stack, ArrayList<String> explored) {
		explored.add(startNode);
		ArrayList<String> edges = graph.get(startNode);
		for(String edge : edges) {
			if(!explored.contains(edge)) {
				depthFirstSearch(graph, edge, stack, explored);
			}
		}
		stack.push(startNode);
	}
	
	public Stack<String> dfs_loop(Map<String, ArrayList<String>> graph) {
		Stack<String> stack = new Stack<String>();
		ArrayList<String> explored = new ArrayList<String>();
		
		for(String vertex : graph.keySet()) {
			if(!explored.contains(vertex)) {
				depthFirstSearch(graph, vertex, stack, explored);
			}
		}
		return stack;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		File file = new File("/home/ragesh/eclipseworkspace/Algorithms-Java/resources/dag");
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String line;
		Map<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
		while((line = bReader.readLine()) != null) {
			ArrayList<String> edges = new ArrayList<String>();
			String[] val = line.split("\t");
			String node = "";
			for(int i = 0; i < val.length; i++) {
				if(i==0) {
					node = val[0];
					continue;
				}
				edges.add(val[i]);
			}
			graph.put(node, edges);
		}
		bReader.close();
		
		TopologicalSort ts = new TopologicalSort();
		Stack<String> result = ts.dfs_loop(graph);
		
		int result_length = result.size();
		System.out.print("Topological Ordering:");
		for(int i = 0; i < result_length; i++) {
			System.out.print(" " + result.pop() + " ");
		}
	}
}
