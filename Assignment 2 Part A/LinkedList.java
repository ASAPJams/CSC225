/* Jamie Kirkwin
 * V00875987
 * CSC 225 Assignment 2A
 */

import java.util.*;
import java.io.*;
public class LinkedList
{
   int n;
   ListNode start;
   ListNode rear;

// You can set debug=true when you are debugging and want
// to print out what your program is doing.
// Please leave your debugging messages in your code when
// you submit it. Change debug back to false before
// submitting your code.

   static final boolean debug= false;
   //To be used as the nDigit param for printBigInteger
   public static final int DEBUG_SIZE = 10; 

   public LinkedList()
   {
       n= 0;
       start= null;
       rear= null;
   }
   public LinkedList(int size, ListNode first, ListNode last)
   {
       n= size;
       start= first;
       rear= last;
   }

   public static LinkedList readBigInteger(Scanner in)
   {
      /* A method that accepts a scanner, constructs a LinkedList representation
       * of an integer using the scanner, and returns that LinkedList
       */

        if(!in.hasNextInt())            // If the scanner has no relevant input 
            return null;                // then the method returns null.

        
        int length = in.nextInt();
        LinkedList x = new LinkedList(length, null, null); // The list that will
                                                           // be returned is initialized
        ListNode cur;       // A reference to be used when appending new nodes
        int tmp;

        for(int i = 0; i < length; i++) {

            // the next integer is read in and stored
            tmp = in.nextInt();

            //a new node containing the next digit is added to the list 
            if(x.start == null) {
                // Special Case: List is empty
                x.start = new ListNode(tmp, null);
                x.rear = x.start;
            }else {
                // The new node is appended to the list
                cur = new ListNode(tmp, x.start);
                x.start = cur;
            }

        }

        // The newly created list is returned
        return x;

   }
   public void reverse(int level)
   {
       Test.checkList(this); // Do not remove or move this statement.

        if(debug) {
            System.out.println("Level: " +level);
            System.out.println();
        }

        if(start == rear)       // Base Case: Empty list or list of size 1
            return;
        else{                  // Recursive Case: 2 or more elements
            ListNode cur = start;
            for(int i = 0; i < n/2 - 1; i++) {
                cur = cur.next;
            }
            // cur is the now at the tail end of L1

            LinkedList l1 = new LinkedList(n/2, start, cur);    // Two sublists are created
            LinkedList l2 = new LinkedList(n- (n/2), cur.next, rear);

            l1.rear.next = null;        // The sublists are seperated

            if(debug) {
                System.out.println("Reversing L1:");
            }
            l1.reverse(level + 1);      // Each sublist is reversed
            if(debug) {
                System.out.println("Reversing L2:");
            }
            l2.reverse(level + 1);
            if(debug) {
                l2.printBigInteger(DEBUG_SIZE);
            }

            l2.rear.next = l1.start;    // The lists are concatenated

            start = l2.start;           // Start and rear pointers are updated
            rear = l1.rear;
        }
   }

/*
    This method returns:
    -1 if the bigInteger associated with the method is less than y
     0 if the bigInteger associated with the method is equal to y
    +1 if the bigInteger associated with the method is greater than y
*/
   public int compare(LinkedList y)
   {
      /* This method compares two bigInteger values.
       * Returns -1 if y is greater than this
       * Returns +1 if this is grearer than y
       * Returns 0 if this equals y
       */

        if(y == null)   // Eliminate null pointer possibility
            return 1;

        ListNode a,b;   // Traversal pointers
        a = this.start;
        b = y.start;

        int flag = 0;   // Return value

        // Traverse each list, comparing the digits in increasing significance
        while(a != null && b != null) {
            if(a.data < b.data)
                flag = -1;
            else if(a.data > b.data)
                flag = 1;
            a = a.next;
            b = b.next;
        }

        // at least one of the lists has been fully traversed.

        if(a == null && b != null) {
        // Check any unvisited nodes in y to see if they are non-zero
            while(b != null) {
                if(flag == -1)      // y must be greater
                    return flag;

                if(b.data > 0)
                    return -1;
                
                b = b.next;
            }
        }else if(a != null && b == null) {
        // Check any unvisited nodes in x to see if they are non-zero
            while(a != null) {
                if(flag == 1)       // x must be greater
                    return flag;

                if(a.data > 0) 
                    return 1;
                
                a = a.next;
            }
        }

        return flag;
   }

// Tries to read in a non-negative integer from the input stream.
// If it succeeds, the integer read in is returned. 
// Otherwise the method returns -1.
   public static int readInteger(Scanner in)
   {
       int n;

       try{
           n= in.nextInt();
           if (n >=0) return(n);
           else return(-1);
       }
       catch(Exception e)
       {
//        We are assuming legal integer input values are >= zero.
          return(-1);
       }
   }

// You can use this in order to get nicer output
// (lined up in columns).

   public void printBigInteger(int nDigit)
   {
        boolean leadingZero;
        ListNode current;
        int i;
        int n_extra;

        reverse(0);
        leadingZero= true;
        if (n < nDigit)
        {
            for (i=n; i < nDigit; i++)
                System.out.print(" ");
        }
        n_extra= n- nDigit;
        current= start;
        while (current != null)
        {
            if (leadingZero)
            {
                if (current.data != 0)
                   leadingZero= false;
            }
            if (leadingZero)
            {
               if (current.next == null) // value is 0.
                   System.out.print(current.data);
               else if (n_extra <= 0)
                   System.out.print(" ");
            }
            else
            {
                System.out.print(current.data);
            }
            n_extra--;
            current= current.next;
        }
        reverse(0);
   }

} 
