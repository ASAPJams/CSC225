import java.util.Random;
public class LinkedList
{ 
	int n;
	ListNode start, rear;
	int a=0,b=0,c=0,d=0;

	public ListNode middleMax(int level) {
	
		// Block A
		LinkedList listA, listB;										//A1
		ListNode rear1, start2, max1, max2;								//A2
		int i, last;													//A3
		if(level == 0) {
			a++;
		}
		if (n==1) {
			if(level == 0) printResults(a,b,c,d);
			return(start);//--------------------------------------//A4
		}


		// Block B

		// beginMax sets last=1, endMax sets last= n-1
		last= n/2; 														//B1			
		rear1= start;//-------------------------------------------------//B2
		if(level == 0) {
			b++;
		}

		for (i=1; i < last; i++) {										//B3
			rear1= rear1.next;//----------------------------------------//B4
			if(level == 0) {
				b++;
			}
		}
		start2= rear1.next;												//B5
		rear1.next= null;												//B6
		listA= new LinkedList(last, start, rear1);						//B7
		listB= new LinkedList(n-last, start2, rear);					//B8
		
		// Block C
		max1= listA.middleMax(level+1);//-------------------------------//C1
		c++;
		max2= listB.middleMax(level+1);//-------------------------------//C2
		c++;

		// Block D
		listA.rear.next= listB.start;//---------------------------------//D1
		if(level == 0) {
			d++;
		}
		if (max1.data > max2.data) return(max1);						//D2
		else{ 
			if(level == 0)printResults(a,b,c,d);
			return(max2);												//D3
		}
	}
	
	public LinkedList(int size, ListNode first, ListNode last) {		//E0 

		// Block E
		n= size;														//E1
		start= first;													//E2
		rear= last;														//E3
	}
	public void printResults(int a, int b, int c, int d) {
			System.out.println("A = " + a);
			System.out.println("B = " + b);
			System.out.println("C = " + c);
			System.out.println("A = " + d);
	}
	public static void main(String[] args) {
		Random r = new Random();

		ListNode start = new ListNode(null, r.nextInt(100));
		ListNode rear = start;
		
		for(int i = 1; i < 1; i++) {
			start = new ListNode(start, r.nextInt(100));	
		}

		LinkedList l = new LinkedList(1,start,rear);
		l.middleMax(0);
	}
}