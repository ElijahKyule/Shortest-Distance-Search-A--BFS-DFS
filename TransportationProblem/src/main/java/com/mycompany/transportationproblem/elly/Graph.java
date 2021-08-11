package com.mycompany.transportationproblem.elly;

import java.io.*; 
import java.util.*; 
class Graph 
{ 
    static String log1 = "", log2 = "", log3 = "";
    public List bList, dList;
    public Iterator i, j;
    public ArrayList BFSlist, DFSlist;
public class Edge{
		int vertex,weight;
               
		public Edge(int v,int w){
			this.vertex=v; this.weight=w;
		}
		@Override
		public String toString(){
			return "("+vertex+","+weight+")";
		}
	}
    private int V;   // No. of vertices 
    private LinkedList<Edge> adj[]; //Adjacency Lists 
  
    Graph(int v) 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    } 
  
   
    void addEdge(int v,int dest,int weight) 
    { 
        Edge e=new Edge(dest,weight);
        adj[v].add(e);
    } 
  @Override
	public String toString(){
		String result="";
               
		for(int i=0;i<adj.length;i++)
                       
			result+=i+"=>"+adj[i]+"\n";
		return result;
	}
    void BFS(int s,int goal) 
    { 
        bList = new ArrayList();
        BFSlist = new ArrayList();
        boolean visited[] = new boolean[V]; 
  
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        LinkedList<Integer> queue1 = new LinkedList<Integer>(); 
        queue1.addLast(0);
        visited[s]=true; 
        queue.add(s); 
        int cost=0,c=0;
        while (queue.size() != 0) 
        { 
            s = queue.poll(); 
            c= queue1.poll();
            bList.add(s);
            cost+=c;
            if(s==goal)break;
           
            Iterator<Edge> i = adj[s].listIterator(); 
            int n;
            
            while (i.hasNext()) 
            { 
                Edge e = i.next();
                n=e.vertex;
                
                if (!visited[n]) 
                { 
                    visited[n] = true; 
                    queue.add(n);
                    queue1.add(e.weight);
                } 
                
            } 
            
        } 
        i = bList.iterator();
        while(i.hasNext()){
            int k = (int) i.next();
            if(k==0){BFSlist.add("Nairobi");}
            else if(k==1){BFSlist.add("Kisumu");}
            else if(k==2){BFSlist.add("Meru");}
            else if(k==3){BFSlist.add("Garissa");}
            else if(k==4){BFSlist.add("Mombasa");}
            
        }
        log1 = BFSlist.toString();
        log2 = Integer.toString(cost);
        log3 = "OPTIMAL ROUTE: " +log1+"\n OPTIMAL DISTANCE: "+log2+" KM\n";
    } 
    void DFS(int s,int goal) 
        { 
            dList = new ArrayList();
            DFSlist = new ArrayList();
            Vector<Boolean> visited = new Vector<Boolean>(V); 
            for (int i = 0; i < V; i++) 
                visited.add(false); 
                  Stack<Integer> stack = new Stack<>(); 
             Stack<Integer> stack1 = new Stack<>();   
            
            stack.push(s);
            stack1.push(0);
            int cost=0,c; 
            while(stack.empty() == false) 
            { 
                s = stack.peek();
                c= stack1.peek();
                stack.pop(); 
                stack1.pop();
                cost+=c;
                
                if(visited.get(s) == false) 
                { 
                    dList.add(s);
                    visited.set(s, true); 
                } 
                if(s==goal)break; 
                
                Iterator<Edge> iterator = adj[s].iterator(); 
                  
                while (iterator.hasNext())  
                { 
                    
                    Edge e = iterator.next();
                    int v=e.vertex;
                    if(!visited.get(v)){
                        stack.push(v); 
                        stack1.push(e.weight);
                    } 
                        
                } 
                  
            }
            j = dList.iterator();
            while(j.hasNext())
            {
                int k = (int) j.next();
                if(k==0){DFSlist.add("Nairobi");}
                else if(k==1){DFSlist.add("Kisumu");}
                else if(k==2){DFSlist.add("Meru");}
                else if(k==3){DFSlist.add("Garissa");}
                else if(k==4){DFSlist.add("Mombasa");}
            
            }
            log1 = DFSlist.toString();
            log2 = Integer.toString(cost);
            log3 = " OPTIMAL ROUTE: " +log1+"\n OPTIMAL DISTANCE: "+log2+" KM\n";
        } 
} 