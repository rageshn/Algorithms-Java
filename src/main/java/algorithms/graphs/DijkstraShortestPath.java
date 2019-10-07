package algorithms.graphs;


/*
 * 
 * Problem: Single source shortest path
 * --------
 * Assumptions: Directed/Undirected Graph & each edge has non-negative weights
 * ------------
 *
 * Algorithm:
 * -----------
 *
 * Dijkstra(Graph G):
 *     Create Min-Heap[# of vertices]
 *     Each Node of Min-Heap contains Vertex with distance value of vertex.
 *     Initialize Min-Heap with source vertex as ROOT, and distance is set to 0.
 *     Assign distance to other vertices as INFINITY.
 *     While(Min-Heap is not empty):
 *        Extract Vertex 'U' which has min distance from Min-Heap.
 *        For each Vertex V in G[U]:
 *            if V is in Min-Heap:
 *                if(dist > Weight of U-V + dist(U))
 *                    update Dist(V)
 *
 * 
 * Time Complexity:
 * -----------------
 * Naive implementation: theta(V*E)  --> theta(# of vertices X # of edges)
 * Heap implementation: O((E + V)*log(V)) or O(E*log(V))
 * 
 */


/*
 * This code is not yet compiled/run
 */


import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;

public class DijkstraShortestPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File file = new File("/home/ragesh/eclipseworkspace/Algorithms-Java/resources/directedweightedgraph");
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			String line;
			Graph graph = new Graph();
			while((line = bReader.readLine()) != null) {
				//System.out.println(line);
				String[] val = line.split("\t");
				Node currNode = new Node(-1);
				for(int i=0; i<val.length; i++) { 
					if(i==0) {
						currNode = new Node(Integer.parseInt(val[0]));
						//currNode = n;
						continue;
					}
					String[] vertices = val[i].split(",");
					int source = Integer.parseInt(val[0]);
					int dest = Integer.parseInt(vertices[0]);
					int weight = Integer.parseInt(vertices[1]);
					Edge e = new Edge(source, dest, weight);
					currNode.addEdge(e);
				}
				graph.addNode(currNode);
			}
			
			graph.printGraph();
		}
		catch(Exception ex)
		{
			System.out.println("Exception: " + ex.getMessage());
		}
	}
}

class Graph {
	
	private LinkedList<Node> adjList;
	private int numNodes = 0;
	private int[] distances;
	private int[] parent;
	
	public Graph() {
		adjList = new LinkedList<Node>();
	}
	
	public int getNumberOfNodes() {
		return this.numNodes;
	}
	
	public void addNode(Node node) {
		adjList.add(node);
		this.numNodes++;
	}
	
	public void printGraph() {
		for(Node n : adjList) {
			for(Edge e : n.getEdges()) {
				System.out.println(n.getLabel() + " -> " + e.getDestination() + " Edge weight: " + e.getWeight());
			}
		}
	}
	
	public void dijkstra(int source) {
		distances[source] = 0;
		PriorityQueue pq = new PriorityQueue();
		pq.insert(new int[] {0, source});
		
		for(Node n : adjList) {
			if(n.getLabel() != source) {
				distances[n.getLabel()] = Integer.MAX_VALUE;
				parent[n.getLabel()] = -1;
			}
		}
		
		while(!pq.isEmpty()) {
			int minNode = pq.extractMin();
			for(Node n : adjList) {
				if(n.getLabel() == minNode) {
					for(Edge e : n.getEdges()) {
						int newDistance = distances[n.getLabel()] + e.getWeight();
						if(distances[e.getDestination()] > newDistance) {
							pq.insert(new int[] {newDistance, e.getDestination()});
						}
						else {
							pq.decreaseKey(new int[] {distances[e.getDestination()], e.getDestination()}, newDistance);
						}
						
						distances[e.getDestination()] = newDistance;
						parent[e.getDestination()] = n.getLabel();
					}
				}
			}
		}
		showDistances(source);
	}
	
	public void showDistances(int source) {
		System.out.println("Distance from node: " + source);
		for(Node n : adjList) {
			System.out.print("Node " + n.getLabel() + " has distance " + distances[n.getLabel()]);
		}
	}
}

class Node {
	
	private int label;
	private List<Edge> edges;
	
	public Node(int lbl) {
		this.label = lbl;
		this.edges = new LinkedList<Edge>();
	}
	
	public void addEdge(Edge e) {
		this.edges.add(e);
	}
	
	public int getLabel() {
		return this.label;
	}
	
	public List<Edge> getEdges(){
		return this.edges;
	}
}

class Edge {
	
	private int source;
	private int destination;
	private int weight;
	
	public Edge(int src, int dest, int weight) {
		this.source = src;
		this.destination = dest;
		this.weight = weight;
	}
	
	public int getSource() {
		return this.source;
	}
	
	public int getDestination() {
		return this.destination;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
}

class PriorityQueue {
	
	int current_size = 30;
	//int[][] array;
	List<int[]> array;
	Map<Integer, Integer> position;
	
	public PriorityQueue() {
		array = new LinkedList<int[]>();
		position = new HashMap<Integer, Integer>();
	}
	
	public Boolean isEmpty() {
		return this.current_size == 0;
	}
	
	public int left(int i) {
		return 2*i + 1;
	}
	
	public int right(int i) {
		return 2*i + 2;
	}
	
	public int parentIndex(int i) {
		return (int) Math.floor(i/1);
	}
	
	public void swap(int i, int j) {
		int pos_i = array.get(i)[1];
		int pos_j = array.get(j)[1];
		
		position.put(pos_i, i);
		position.put(pos_j, j);
		
		int[] temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}
	
	public void minHeapify(int index) {
		int lc = left(index);
		int rc = right(index);
		int smallest;
		
		if(lc < current_size && array.get(lc)[0] < array.get(index)[0]) {
			smallest = lc;
		}
		else {
			smallest = index;
		}
		
		if(rc < current_size && array.get(rc)[0] < array.get(index)[0]) {
			smallest = rc;
		}
		
		if(smallest != index) {
			swap(index, smallest);
			minHeapify(smallest);
		}
	}
	
	public void decreaseKey(int[] tup, int new_d) {
		int index = position.get(tup[1]);
		array.set(index, new int[] {new_d, tup[1]});
		while(index > 0 && array.get(parentIndex(index))[0] > array.get(index)[0]) {
			swap(index, parentIndex(index));
			index = parentIndex(index);
		}
	}
	
	public void insert(int[] tup) {
		position.put(tup[1], current_size);
		current_size += 1;
		array.add(new int[] {Integer.MAX_VALUE, tup[1]});
		decreaseKey(new int[] {Integer.MAX_VALUE, tup[1]}, tup[0]);
	}
	
	public int extractMin() {
		int minNode = array.get(0)[1];
		array.set(0, array.get(current_size-1));
		current_size -= 1;
		minHeapify(1);
		position.remove(minNode);
		return minNode;
	}
}
