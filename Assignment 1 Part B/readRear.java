import java.util.*;

public class readRear {	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		readRear(sc);
	}

	public void readRear(Scanner in) { 
		
		ListNode tmp, current; int data; int i;
		n= readInteger(in);
		start=null; rear=null;
		
		for (i=0; i < n; i++) {
		
			data= readInteger(in);
			tmp= new ListNode(data, null);
		
			if (i==0) { 
				start=tmp;
			}
			else {
				//Checkpoint 1.
				current= start;
				
				while(current.next != null) {
					
					current= current.next; // Statement to count.
					//Checkpoint 2.
				}
				
				current.next= tmp;
			}
			rear= tmp;
		}

		// Checkpoint 3.
	}
}