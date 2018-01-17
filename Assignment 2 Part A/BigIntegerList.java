/* Jamie Kirkwin
 * V00875987
 * CSC 225 Assignment 2A
 */

import java.util.*;
import java.io.*;
/* This class is for creating a singly linked class of big integer nodes. */
/* Hand this in together with your LinkedList.java. */
class BigIntegerList
{
   int n; // Number of items in the list.
   BigIntegerNode start;
   BigIntegerNode rear;

// You can set debug=true when you are debugging and want
// to print out what your program is doing.
// Please leave your debugging messages in your code when
// you submit it. Change debug back to false before
// submitting your code.
   static final boolean debug= false;

   public BigIntegerList()
   {
       n=0;
       start= null;
       rear= null;
   }

   public static BigIntegerList readBigIntegerList(Scanner in)
   {
    /* A method that accepts a Scanner, creates a BigIntegerList 
     * based on the contents of the Scanner, and returns that
     * BigIntegerList.
     */
       int length;

       try{
            length = in.nextInt();
       }catch(Exception e) {
            return null; //return null if there is no input
       }

       BigIntegerList list = new BigIntegerList();
       BigIntegerNode node;

       for(int i = 0; i < length; i++) {
            // create each node, then add the node to the end (rear) of the list
            node = new BigIntegerNode(LinkedList.readBigInteger(in), null);
            list.n++;

            // add the node to the list 
            if(list.start == null) { // empty list
                list.start = node;
            }else {
                list.rear.next = node;
            }
            list.rear = node;    
       }

       return list; 
   }
   public void printBigIntegerList()
   {
        // This method prints a big integer list. 

        BigIntegerNode cur = start;
        for(int i = 0; i < n; i++) {
            
            // Seperate different values
            if(i != 0) {
                System.out.print(", ");
            }

            // Allow only 10 different values per line of output
            if(i % 10 == 0) {
                System.out.println();
            }


            cur.x.printBigInteger(8);
            cur = cur.next;
        }
        
        // Subsequent output starts on a new line.
        System.out.println();


   }
   public void quickSort(int level)
   {
    /* A recursive quickSort implimentation
     * The level parameter is used to track recursion depth
     * The list is sorted recursively by creating 3 seperate lists 
     * of values less than, equal to, and greater than a selected
     * 'pivot' value, sorting the lists, and combining them.
     */

        // Base Case
        if(n <= 1)
            return;

        // Create 3 new lists 
        BigIntegerList List1, List2, List3;
        List1 = new BigIntegerList();
        List2 = new BigIntegerList();
        List3 = new BigIntegerList();
            
        // Set the pivot value to be the big integer value that 
        // appears first on the linked list. 
        LinkedList pivot = start.x;

        int cmpr;
        BigIntegerNode tmp;
        
        // Traverse the list removing each cell and placing it at the
        // end of the appropriate list 

        while(start != null) {
            cmpr = start.x.compare(pivot);
            tmp = start.next;

            if(cmpr == -1) {
                // add to List1
                List1.add(start);
                
            }else if(cmpr == 0) {
                // add to List2
                List2.add(start);

            }else {
                // add to List 3
                List3.add(start);
            }

            start = tmp;
            // Used because the start node's next pointer is 
            // manipulated when it is added to a different list
            
            n--; // Start has been removed from the BigIntegerList
        }

        // Sort List1 and List3 recursively. 
        List1.quickSort(level + 1);
        List3.quickSort(level + 1);

        // The final answer is List1 followed by List2 followed by List3.

        if(List1.start == null) {
            //List1 is empty
            this.start = List2.start;
        }else {
            this.start = List1.start;
            List1.rear.next = List2.start;
        }

        if(List3.start == null) {
            // List3 is empty
            this.rear = List2.rear;
        }else {
            this.rear = List3.rear;
            List2.rear.next = List3.start;
        }
    
        this.n = List1.n + List2.n + List3.n;
    }

    // A private helper method that adds a node to the beginning 
    // of a bigIntegerList    
    private void add(BigIntegerNode node) {
        if(node == null)
            return;

        node.next = start;
        start = node;

        if(rear == null)
            rear = node;
        n++;
    }
}
