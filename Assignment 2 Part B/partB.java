/*
This method returns:
-1 if the bigInteger associated with the method is less than y
0 if the bigInteger associated with the method is equal to y
+1 if the bigInteger associated with the method is greater than y
*/
public int compare(LinkedList y)
{
	int nv;
	// If one has more digits than the other, check if any
	// of the extra digits are non-zero- if so, the one with
	// more digits is bigger.

	// Block 1

	nv= n; // Change made from first version.

	if (n > y.n)
	{
		if (nonZeroDigit(y.n)) 
			return(1);
		
		nv= y.n;
	}else if (y.n > n) {
		if (y.nonZeroDigit(n)) 
			return(-1);
	}
	
	// Block 2
	
	// Compare digits starting with most significant ones.
	return(compareDigit(nv, y));
}

public boolean nonZeroDigit(int nIgnore)
{
	ListNode current;
	int i;
	current= start;

	for (i=0; i < nIgnore; i++)
		current= current.next;
	
	while (current != null)	{

		if (current.data != 0) 
			return(true);
		
		current= current.next;
	}

	return(false);
}

public int compareDigit(int nv, LinkedList y) {
	
	int dx, dy;
	ListNode xcurrent, ycurrent;
	int i;
	
	if (nv ==0) 
		return(0); // They are equal.
	
	// Get xcurrent/ycurrent to point to cell nv of x/y.
	xcurrent= start;
	ycurrent= y.start;
	
	for (i= 1; i < nv; i++) {
		xcurrent= xcurrent.next;
		ycurrent= ycurrent.next;
	}
	
	dx= xcurrent.data;
	dy= ycurrent.data;
	if (dx < dy)
		return(-1);
	
	if (dx > dy) 
		return( 1);
	
	return(compareDigit(nv-1, y));
}
