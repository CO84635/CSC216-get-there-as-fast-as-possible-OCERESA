package csc216.get.there.as.fast.as.possible.oceresa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class App {
    static int dijkstras(Map<Integer, List<int[]>> adjacencyMap, int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int[] weight = new int[adjacencyMap.size() + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[start] = 0;

        pq.offer(new int[]{0, start});

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0];
            int w = current[1];

            if (w == end) {
                return d;
            }

            for (int[] neighbor : adjacencyMap.getOrDefault(w, new ArrayList<>())) {
                int v = neighbor[0];
                int weightNeighbor = (neighbor.length > 1) ? neighbor[1] : 1; 

                if (weight[v] > weight[w] + weightNeighbor) {
                    weight[v] = weight[w] + weightNeighbor;
                    
                    pq.offer(new int[]{weight[v], v});
                }
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            graph.put(1, Arrays.asList(new int[]{2, 1}, new int[]{3, 1}, new int[]{5, 1}));
            graph.put(2, Arrays.asList(new int[]{4, 1}));
            graph.put(3, Arrays.asList(new int[]{5, 1}));
            graph.put(4, Arrays.asList(new int[]{3, 1}));
            graph.put(5, new ArrayList<>());
    
            System.out.println("Shortest Distance from 1 to 2: " + App.dijkstras(graph, 1, 2));
            System.out.println("Shortest Distance from 1 to 5: " + dijkstras(graph, 1, 5));
            System.out.println("Shortest Distance from 2 to 5: " + dijkstras(graph, 2, 5));
            System.out.println("Shortest Distance from 5 to 1: " + dijkstras(graph, 5, 1));
            System.out.println("Shortest Distance from 2 to 1: " + dijkstras(graph, 2, 1));
        }

        {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            graph.put(1, Arrays.asList(new int[]{2, 4}, new int[]{3, 2}));
            graph.put(2, Arrays.asList(new int[]{4, 5}));
            graph.put(3, Arrays.asList(new int[]{5, 10}));
            graph.put(4, Arrays.asList(new int[]{5, 2}));
            graph.put(5, new ArrayList<>());
    
            System.out.println("Shortest Distance from 1 to 5: " + App.dijkstras(graph, 1, 5));
            System.out.println("Shortest Distance from 1 to 4: " + App.dijkstras(graph, 1, 4));
            System.out.println("Shortest Distance from 2 to 5: " + App.dijkstras(graph, 2, 5));
            System.out.println("Shortest Distance from 3 to 5: " + App.dijkstras(graph, 3, 5));
            System.out.println("Shortest Distance from 5 to 1: " + App.dijkstras(graph, 5, 1));
        }
        
    }
}
