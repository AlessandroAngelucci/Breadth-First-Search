// Breadth First Search
// Details available at https://en.wikipedia.org/wiki/Breadth-first_search

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph {

    private LinkedList<Integer>[] adjList;  // Adjacency list to indicate which vertices are connected by an edge

    public Graph(int size){  // Constructor receiving desired number of vertices
        adjList = new LinkedList[size];  // Create adjacency list of desired size
        for(int i = 0; i < size; i++){  // For each vertex in list
            adjList[i] = new LinkedList<>();  // Sublist to store edges
        }
    }

    public void addEdge(int parent, int child){  // Add an edge between parent and child vertices
        adjList[parent].add(child);  // Inserts the child vertex into the parent's edge list
        adjList[child].add(parent);  // Inserts the parent vertex into the child's edge list
    }

    public int BFS(int end){  // Returns minimum distance between root and end vertex using BFS
        int start = 0;  // Start from root

        Queue<Integer> toVisit = new LinkedList<>();  // Queue of vertices to visit
        int[] parents = new int[adjList.length];  // Array to store each vertex's parent
        int[] visited = new int[adjList.length];  // Array to store flags indicating if a vertex has been visited

        toVisit.add(start);  // Add root to queue
        parents[start] = -1;  // Root has no parent vertex
        visited[start] = 1;  // Set root flag as visited

        while(!toVisit.isEmpty()){  // While queue is not empty
            int current = toVisit.poll();  // Store and remove queue head
            if(current == end) break;  // If current vertex is desired vertex exit while loop

            for(int i: adjList[current]){  // For each vertex connected to current vertex
                if(visited[i] == 0){  // If vertex is unvisited
                    toVisit.add(i);  // Add vertex to queue
                    parents[i] = current;  // Set vertex parent to current vertex
                    visited[i] = 1;  // Set vertex flag as visited
                }
            }
        }

        int current = end;  // Set current vertex to desired vertex
        int distance = 0;  // Distance between root and end vertex
        System.out.println();  // Print blank line
        while(parents[current] != -1){  // While current vertex is not root
            System.out.printf("%d -> ", current);  // Print current vertex
            current = parents[current];  // Set current vertex to parent vertex
            distance++;  // Increment distance
        }
        System.out.printf("%d\n", start); // Print root

        return distance;  // Return distance between root and end vertex
    }

    public static void main(String[] args){  // Main method to get user input and perform BFS
        Scanner sc = new Scanner(System.in);  // Create a scanner for keyboard input
        System.out.println("Enter the number of vertices and edges: ");  // Prompt user input for number of vertices and edges
        int vertices = sc.nextInt();  // Store number of vertices
        int edges = sc.nextInt();  // Store number of edges

        Graph graph = new Graph(vertices);  // Create a new graph
        System.out.printf("Enter %d edges (root = 0, maximum vertex = %d): \n", edges, vertices-1);  // Prompt user input for edge endpoints
        for(int i = 0; i < edges; i++){  // For each desired edge
            graph.addEdge(sc.nextInt(), sc.nextInt());  // Add edge between parent and child
        }

        System.out.println("Enter desired vertex: ");  // Prompt user input for desired vertex
        int end = sc.nextInt();  // Store desired vertex

        if(end < 0 || end >= vertices){  // If vertex does not exist
            System.out.printf("\nGraph does not contain desired vertex '%d'\n", end);  // Print not contained message
            return;  // Exit
        }

        int distance = graph.BFS(end);  // Calculate distance between root and desired vertex
        System.out.printf("\nMinimum distance between root and %d is %d\n", end, distance);  // Print distance
    }
}