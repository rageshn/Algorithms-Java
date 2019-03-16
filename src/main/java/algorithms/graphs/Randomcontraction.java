package algorithms.graphs;

import java.io.*;
import java.util.*;

public class Randomcontraction {
	
	public void kargerMinCut(Map<Integer, List> graph) {
		while(graph.size() > 2) {
			Random rand = new Random();
			Object[] keys = graph.keySet().toArray();
			int startIndex = rand.nextInt(keys.length);
			int start = Integer.parseInt(keys[startIndex].toString());
			List finishList = graph.get(start);
			int finishIndex = rand.nextInt(finishList.size());
			int finish = Integer.parseInt(finishList.get(finishIndex).toString());
			
			
			List fin = graph.get(finish);
			
			for(int i=0; i<fin.size(); i++) {
				int edge = Integer.parseInt(fin.get(i).toString());
				if(edge != start) {
					graph.get(start).add(edge);
				}
			}
			
			for(int i=0; i<fin.size(); i++) {
				int edge = Integer.parseInt(fin.get(i).toString());
				graph.get(edge).remove(Integer.valueOf(finish));
				if(edge != start) {
					graph.get(edge).add(start);
				}
			}
			
			graph.remove(Integer.valueOf(finish));
		}
		
		for(Map.Entry<Integer, List> entry: graph.entrySet()) {
			System.out.println("Key: " + entry.getKey() + " , Values: " + entry.getValue());
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("/home/ragesh/eclipseworkspace/Algorithms-Java/src/main/java/algorithms/graphs/graph");
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		String line;
		Map<Integer, List> graph = new HashMap<Integer, List>();
		while((line = bReader.readLine()) != null)
		{
			List edges = new ArrayList();
			String[] val = line.split("\t");
			int node = 0;
			for(int i=0; i<val.length; i++) {
				if(i==0) {
					node = Integer.parseInt(val[0]);
					continue;
				}
				edges.add(val[i]);
			}
			graph.put(node, edges);
		}
		
		Randomcontraction rc = new Randomcontraction();
		rc.kargerMinCut(graph);
	}
}
