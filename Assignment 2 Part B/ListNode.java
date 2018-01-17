public class ListNode {
	ListNode next;
	int data;

	public ListNode() {
		next = null;
		data = 0;
	}

	public ListNode(ListNode nextNode, int theData) {
		data = theData;
		next = nextNode;
	}
}