/*
 *  Jamie Kirkwin
 *  V00875987
 */

import java.util.*;
import java.io.*;
public class LinkedList
{
    
    int n;
    ListNode start;
    ListNode rear;

    //Used only to control debug print statements
    public static boolean debug = false;

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
    public void printBigInteger()
    {
    /* A method that prints the integer represented by the list by reversing
     * the list, traversing the list and recording its contents, and then 
     * reversing it again to return it to its original state
     */

        reverse(0); // The list is reversed

        String number = "";     // To be returned
        ListNode cur = start;   
        boolean skip = true;    // Used to skip any leading zeros

        while(cur != null) {    // Iterate through each node in the lists
            if(cur.data != 0)
                skip = false;   // If a non-zero value is reached then 
                                // all subsequent zeros are significant
            if(!skip) {
                // Have passed any leading zeros
                number = number + cur.data;
            }

            cur = cur.next;     
        }

        reverse(0);         //List is returned to its original order
       
        if(number == "")    // If the list is empty 0 should be printed 
            number = "0";   // rather than an empty String
       
        System.out.print(number);       // Final result is printed
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
                l2.printList();
            }

            l2.rear.next = l1.start;    // The lists are concatenated

            start = l2.start;           // Start and rear pointers are updated
            rear = l1.rear;
        }


    }
    public void plus_plus() 
    { 
    /* A method that increments the value represented by the list, lengthening 
     * it if necessary
     */
      
        if(n == 0) {        // Special Case: Empty list
            n = 1;          // A new node is created and given a data value of 1
            start = new ListNode(1, null);
            rear = start;
        }else {

            ListNode cur = start;       // Pointer used to traverse the list

            // Each node is incremented, until either the list has been fully 
            // traversed, or a node with a value less than 9 is incremented
            while(cur != null) {      
             increment(cur);            // A helper function is called on the current node which increments it         
                if(cur.data != 0)       // If the node just incremented did not wrap from 9 to 0, then the loop is over
                    break;
                else if(cur.next == null) {         // If the rear of the list is reached, then a 1 must be appended
                        ListNode node = new ListNode(1, null);
                        cur.next = node;
                        rear = node;
                        n++;
                        break;
                }else {
                    cur = cur.next;     // The last node incremented was a 9, so cur is set to the next node which will be incremented
                }
            }
        }
    }

    private void increment(ListNode p) {
    /* A private "helper method" that increases the value of the 
     * data field of the ListNode passed if that value is less than
     * 9; if the value is 9 then it is set to 0. Behavior is undefined
     * for data values not in [0,9]
     */

        if(p.data == 9) {
            p.data = 0;
        }
        else {
            p.data++;
        }
    }
    public LinkedList plus(LinkedList y)
    {
    /* A method that adds the two integers represented by this LinkedList
     * and the LinkedList passed, and returns that value's LinkedList
     * representation
     */
        LinkedList z = new LinkedList();  // The new LinkedList is created

        int sum;
        int carry = 0;
        ListNode thisPtr, yPtr;         // Pointers to traverse the two lists to be added
        ListNode newNode;               // The variable to be used when adding ListNodes to z

        thisPtr = this.start;
        yPtr = y.start;

        while(thisPtr != null || yPtr != null) {        // The loop continues as long as at least one 
                                                        // of the lists contains unprocessed nodes
            sum = carry;    // Sum begins with the carry value from the previous iteration
            carry = 0;   
            
            if(yPtr != null) {      // If appropriate, the data value of the current node in y is added to the sum
                sum += yPtr.data;
                yPtr = yPtr.next;
            }
            
            if(thisPtr != null) {   // If appropriate, the data value of the current node in the list is added to the sum
                sum += thisPtr.data;
                thisPtr = thisPtr.next;
            }

            while(sum >= 10) {      // The carry value is calculated and sum is decreased to a legal value
                carry++;
                sum -= 10;
            }

            newNode = new ListNode(sum, null);  // A new node of data value equal to sum is added to z
            if(z.start == null)
                z.start = newNode;
            else
                z.rear.next = newNode;
            z.rear = newNode;
            z.n = z.n + 1;

        }

        if(carry != 0) {        // If a carry is present after processing both lists, 
                                // one last node must be added to z
            z.rear.next = new ListNode(carry, null);
            z.rear = z.rear.next;
            z.n = z.n + 1;
        }


        return z;       // The completed list containing the sum is returned
    }



// You can use these routines for this assignment:

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
//          We are assuming legal integer input values are >= zero.
            return(-1);
        }
    }

// Use this for debugging only.

    public void printList()
    {
        ListNode current;

        int count=0;

        current= start;

        while (current != null)
        {
            count++;
           
            System.out.println("Item " + count + " in the list is " 
                            + current.data);
            current= current.next;
        }
    }
}


