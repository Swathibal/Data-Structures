import java.util.*;

public class GraphHashMap {
    public class Node  // Node which contains label member variable
    {
        String label;
        
        public Node(String lab){
            this.label = lab;
        }
        
        @Override
        public String toString()
        {
            return label;
        }
    }

    Map<String,Node> vertices = new HashMap<String,Node>();  // hashmap which stores the label and node
    Map<Node,List<Node>> adj = new HashMap<Node,List<Node>>(); // hashmap which stores the node and their adjacency lists
    
    public void addNodes(String Nodevalue)  // to add nodes in a graph
    {
        Node node = new Node(Nodevalue);
        vertices.putIfAbsent(Nodevalue, node);
        adj.putIfAbsent(node, new ArrayList<Node>());
    }

    public void addEdges(String start,String end)  // to add edges in a graph
    {
        Node from = vertices.get(start);
        Node to = vertices.get(end);
        adj.get(from).add(to);
    }

    public void print()  // print the nodes and their adjacency lists
    {
        System.out.println(adj);
        System.out.println( adj.keySet() );
        for (Node source : adj.keySet()) {
            List<Node> target = adj.get( source );
            if (!target.isEmpty()) {
                System.out.println( source + " is connected to " + target );
            }
        }
    }

    public void removeNode(String label)  // to remove the node in a graph
    {
        Node removeNode = vertices.get(label);
        if(removeNode  == null)
          return;
        
        for(Node n : adj.keySet())
        {
            adj.get(n).remove(removeNode);
        }
        adj.remove(removeNode);
        vertices.remove(removeNode);
    }

    public void removeEdge(String start,String end)  // to remove the edge from a graph
    {
        Node from = vertices.get(start);
        Node to = vertices.get(end);

        if(from == null || end == null)
          return;
        
        adj.get(from).remove(to);
        // adj.get(to).remove(from); if graph is a undirected graph
    }

    public void DFS(String label)  // DFS traversal using recursion
    {
        Node root = vertices.get(label);
        Set<Node> visited = new HashSet<>();
        dfs_recursion(root,visited);
    }

    private void dfs_recursion(Node root,Set<Node> visited)  
    {
        System.out.print(root);
        visited.add(root);
        for(Node n : adj.get(root))
        {
            if(!visited.contains(n))
               dfs_recursion(n, visited);
        }
    }

    public void dfs_stack(String label)  // DFS traversal using Stack data structure
    {
        Set<Node> visited = new HashSet<>();
        Stack<Node> s = new Stack<Node>();
        Node root = vertices.get(label);
        s.push(root);
        visited.add(root);
        while(!s.isEmpty())
        {
            Node v = s.pop();
            System.out.print(v);
            for(Node n : adj.get(v))
            {
                if(!visited.contains(n)){
                   visited.add(n);
                   s.push(n);
                }
            }
        }
    }

    public void bfs(String label)  // BFS travesal using QUEUE data structure
    {
        Node root = vertices.get(label);
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(root);
        visited.add(root);
        
        while(!queue.isEmpty())
        {
            Node n = queue.poll();
            System.out.print(n);
            for(Node i : adj.get(n))
            {
                if(!visited.contains(i))
                {
                    visited.add(i);
                    queue.add(i);
                }

            }

        }
    }

    public void hasCycleCheck()  // to check for the cycle in a graph
    {
        Set<Node> all = new HashSet<Node>();
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();
        all.addAll(vertices.values());
            while(!all.isEmpty())
            {
                Node n = all.iterator().next();
                if(hasCycle(n,visiting,visited,all))
                   return true;
          }
        
         return false;
    }

    private boolean hasCycle(Node n,Set<Node> visiting,Set<Node> visited,Set<Node> all)
    {
        visiting.add(n);
        all.remove(n);
        for(Node i : adj.get(n))
        {
            if(visited.contains(i)){
               continue;
            }
            
            if(visiting.contains(i)){
               return true;
            }

            var result = hasCycle(i,visiting,visited,all);

            if(result)
               return true;
        }
        visiting.remove(n);
        visited.add(n);

        return false;
    }
    public static void main(String args[])
    {
        GraphHashMap g = new GraphHashMap();

        g.addNodes("A");  // add nodes in a graph
        g.addNodes("B");
        g.addNodes("C");
        g.addNodes("D");
        g.addNodes("E");
    
        g.addEdges("A","B");  // add edges in a graph
        g.addEdges("A","D");
        g.addEdges("B","E");
        g.addEdges("B","C");
        g.addEdges("C","E");
        g.addEdges("D","A");
  
        g.print();
        g.removeNode("A");   //remove node
        g.removeEdge("A","D"); // remoove edge
        g.print();
        g.DFS("A");   // DFS traversal using recursion
        System.out.println();
        g.dfs_stack("A");  //DFS traversal using stack
        System.out.println();
        g.bfs("A");  //bfs traversal using queue

        System.out.println(g.hasCycleCheck());  // to check whether the graph has cycle or not
        
        
    }
}

