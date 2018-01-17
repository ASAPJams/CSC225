// Jamie Kirkwin
// CSc 225 Assignment 5, Fall 2017
// V00875987

/*
   The WeightedEdgeNode class is provided to you for CSC 225
   by Wendy Myrvold.  You do not have permission to distribute
   this code or to use it for any other purpose.

   You should add code for edgeSort and minWeightTree.
   Otherwise, do not modify the code.

*/

import java.util.*;
import java.io.*;
public class WeightedEdgeList
{
    public static boolean debug= false;

    int n;
    int m;
    WeightedEdgeNode start;
    WeightedEdgeNode rear;

    public void edgeSort()
    {

        // find the maximum length needed
        int size = n;
        WeightedEdgeNode ptr = start;
        for(int i = 0; i < m; i++) 
        {
            if(ptr.edge[0] >= size) 
            {
                size = ptr.edge[0] + 1;
            }
            ptr = ptr.next;
        }

        // we will use this array of lists to store our sublists during the radix sort
        WeightedEdgeList[]  arr = new WeightedEdgeList[size];

        if(debug) System.out.println("\nSize:"+ size +'\n');

        // initialize array
        for(int i = 0; i < arr.length; i++) 
            arr[i] = new WeightedEdgeList();

        // Main Loop - Radix Sort
        for(int j = 2; j >= 0; j--) 
        {   
            // Create sublists and clear this list
            while(start != null)
            {
                ptr = start;
                start = start.next;
                arr[ptr.edge[j]].addRear(ptr);
            }
            rear = null;
            m = 0;

            //Concatenate the lists and restore this list
            for(int i = 0; i < arr.length; i++)
            {
                if(arr[i].rear != null)
                {
                    if(m == 0) 
                    {
                        start = arr[i].start;
                        m = arr[i].m;
                    }
                    else 
                    {
                        rear.next = arr[i].start;
                        m += arr[i].m;
                    }
                    rear = arr[i].rear;

                    // Clear the sublist 
                    arr[i].start = null;
                    arr[i].rear = null;
                    arr[i].m = 0;
                }
            }
            
        }

    }

/*  You should assume this method is called with the edges
    already sorted by weight. The two tasks have been 
    separated so that you can get part marks if one of your
    methods works but the other one does not.
*/
    public WeightedEdgeList minWeightTree()
    {
        UnionFind uF = new UnionFind(n);
        WeightedEdgeList spanning = new WeightedEdgeList(n, 0, null, null);

        // This loop removes edges from the front of the list and places them in the 
        // spanning list to be returned
        WeightedEdgeNode p = start;
        while(p==start && p!=null)
        {
            if(!uF.same_component(p.edge[1], p.edge[2]))
            {
                // Update uF
                uF.union(p.edge[1], p.edge[2]);

                // Remove edge from original list
                start = start.next;
                m--;
                p.next = null;
                if(start == null) rear = null;

                // Add the edge to spanning list
                spanning.addRear(p);

                // update pointer
                p = start;

            }else break; // The current starting edge is to stay in original list
        }

        // If our original list is empty or contains only one element we can return here
        if(m <= 1) 
            {
                if(debug)
                {
                    System.out.println("-------Returning from minWeightTree-------");
                    System.out.println("Chords:");
                    System.out.println("n = " + n);
                    System.out.println("m =  " + m);
                    System.out.println("minWeightTree:");
                    System.out.println("n = " + spanning.n);
                    System.out.println("m =  " + spanning.m);
                }
                return spanning;
            }

        // This loop removes all non-start edges from the list that are 
        // needed for the spanning tree(s) via a look-ahead tactic 
        p = start;
        WeightedEdgeNode tmp;
        while(p.next != null)
        {
            if(!uF.same_component(p.next.edge[1], p.next.edge[2]))
            {   
                // Update uF
                uF.union(p.next.edge[1], p.next.edge[2]);

                // This is necessary since addRear() null-terminates spanning list
                tmp = p.next;

                // If we are removing the rear we move the rear pointer back one node
                if(tmp == rear)
                {
                    rear = p;
                }

                // Remove edge from original list, also ensures null-termination 
                p.next = p.next.next;
                m--;

                // Add edge to spanning list
                spanning.addRear(tmp);

            }else p = p.next;

        }

        if(debug)
        {
            System.out.println("-------Returning from minWeightTree-------");
            System.out.println("Chords:");
            System.out.println("n = " + n);
            System.out.println("m =  " + m);
            System.out.println("minWeightTree:");
            System.out.println("n = " + spanning.n);
            System.out.println("m =  " + spanning.m);
        }
        return spanning;
    }


// Do not make any changes to code below this line:
//----------------------------------------------------------//

    public WeightedEdgeList(int num_vertex, int num_edge, 
              WeightedEdgeNode start_node, WeightedEdgeNode rear_node)
    {
        n=num_vertex;
        m=num_edge;
        start= start_node;
        rear= rear_node;
    }

    public WeightedEdgeList()
    {
        n=0;
        m=0;
        start= null;
        rear= null;
    }

/*  
    The input format consists of two integers
    n m 
    where 
    n is the number of nodes of the graph, and
    m is the number of edges of the graph.
    Legal edge weights are always greater than or equal to 1.
    Then for each edge (u, v) with weight w the input contains:
    w  u  v
*/

    public static WeightedEdgeList readWeightedEdgeList(Scanner in)
    {
        WeightedEdgeList G;
        WeightedEdgeNode add_node;

        int num_edge;
        int w, u, v;
        int i;

        if (! in.hasNextInt()) return null;

        G= new WeightedEdgeList();
        G.n= in.nextInt();

        if (! in.hasNextInt())
        {
           System.out.println("Missing value for m.");
           return(null);
        }

        num_edge= in.nextInt();

        if (debug) System.out.println("n= " + G.n + "  m= " + num_edge);

        for (i=0; i < num_edge; i++)
        {
            if (! in.hasNextInt())
            {
               System.out.println("Error- Missing weight for edge " + (i+1));
               return(null);
            }
            w= in.nextInt();
            if (w < 1)
            {
               System.out.println("Error- Invalid edge weight " + w);
               return(null);
            }

            if (! in.hasNextInt())
            {
               System.out.println("Error- Missing u for edge " + (i+1));
               return(null);
            }
            u= in.nextInt();

            if (! in.hasNextInt())
            {
               System.out.println("Error- Missing v for edge " + (i+1));
               return(null);
            }
            v= in.nextInt();

            if (u < 0 || v < 0 || u >= G.n || v >= G.n)
            {
                System.out.println("Error- Invalid edge: " + u + " " + v);
                return(null);
            } 

            add_node= new WeightedEdgeNode(w, u, v, null);
            G.addRear(add_node);
        }
        return(G);
    }
    public void addRear(WeightedEdgeNode add_node)
    {
        if (start == null)
        {
            start= add_node;
        }
        else
        {
            rear.next= add_node;
        }
        rear= add_node;
        rear.next= null;
        m++;
    }

    public void printWeightedEdgeList()
    {
        WeightedEdgeNode current;

        int n_per_line=5;
        int num;
        int i;

        current= start;
        num=0;
        while (current != null)
        {
            System.out.format("[%3d (%3d, %3d)] ", current.edge[0], 
                                      current.edge[1], current.edge[2]);
            num++;
            if (num== n_per_line)
            {
               System.out.println();
               num=0;
            }
            current= current.next;
        }
        if (num!= 0) System.out.println();
    }
}
