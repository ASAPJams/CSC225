import java.util.Scanner;
public class MyTester {
	public static void main(String[] args) {
		test1(); //passes
		test2(); //passes
		test3();
	}

	public static void test1() {
		System.out.println("Testing: readBigInteger, printBigInteger, reverse");
		LinkedList l = new LinkedList();
		Scanner sc = new Scanner("");
		
		if(l.readBigInteger(sc) != null) {
			System.out.println("Failed");
			return;
		}

		sc = new Scanner("1 1");
		l.readBigInteger(sc).printBigInteger(); 
		System.out.println(" Should be 1");

		sc = new Scanner("2 1 2");
		l.readBigInteger(sc).printBigInteger();
		System.out.println(" Should be 12");

		sc = new Scanner("3 1 2 3");
		l.readBigInteger(sc).printBigInteger();
		System.out.println(" Should be 123");

		sc = new Scanner("10 1 3 5 7 2 8 7 5 3 5");
		l.readBigInteger(sc).printBigInteger();
		System.out.println(" Should be 1357287535");

		System.out.println("Test complete.");
		System.out.println();
	}

	public static void test2() {
		System.out.println("Testing: plus_plus");
		
		LinkedList l;
		Scanner sc;

		sc = new Scanner("0"); //empty list
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();		


		sc = new Scanner("1 1"); //one item
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();


		sc = new Scanner("1 9");               //one item to 2
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();


		sc = new Scanner("2 4 4");			//2 items
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();		


		sc = new Scanner("2 1 9");			//2 digits to w carry
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();	


		sc = new Scanner("2 9 9");			//2 digits to 3
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();	

		
		sc = new Scanner("9 9 9 9 9 9 9 9 9 9 9");
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();				


		sc = new Scanner("9 9 9 9 9 8 9 9 9 9 9");
		l = LinkedList.readBigInteger(sc);
		l.printBigInteger();
		System.out.println();
		l.plus_plus();
		l.printBigInteger();

		System.out.println();
		System.out.println();
	
		System.out.println("Test Complete.");
	}

	public static void test3() {
		System.out.println("Testing: plus");

		Scanner s1, s2;
		LinkedList a, b, c;

		s1 = new Scanner("1 1");
		s2 = new Scanner("1 1");
		a = LinkedList.readBigInteger(s1);
		b = LinkedList.readBigInteger(s2);
		c = a.plus(b);
		a.printBigInteger();
		System.out.print(" + ");
		b.printBigInteger();
		System.out.print(" = ");
		c.printBigInteger();
		System.out.println();
	

		s1 = new Scanner("1 5");
		s2 = new Scanner("1 1");
		a = LinkedList.readBigInteger(s1);
		b = LinkedList.readBigInteger(s2);
		c = a.plus(b);
		a.printBigInteger();
		System.out.print(" + ");
		b.printBigInteger();
		System.out.print(" = ");
		c.printBigInteger();
		System.out.println();


		s1 = new Scanner("2 1 1");
		s2 = new Scanner("3 1 0 0");
		a = LinkedList.readBigInteger(s1);
		b = LinkedList.readBigInteger(s2);
		c = a.plus(b);
		a.printBigInteger();
		System.out.print(" + ");
		b.printBigInteger();
		System.out.print(" = ");
		c.printBigInteger();
		System.out.println();

		s1 = new Scanner("1 0");
		s2 = new Scanner("1 0");
		a = LinkedList.readBigInteger(s1);
		b = LinkedList.readBigInteger(s2);
		c = a.plus(b);
		a.printBigInteger();
		System.out.print(" + ");
		b.printBigInteger();
		System.out.print(" = ");
		c.printBigInteger();
		System.out.println();

		s1 = new Scanner("1 1");
		s2 = new Scanner("1 0");
		a = LinkedList.readBigInteger(s1);
		b = LinkedList.readBigInteger(s2);
		c = a.plus(b);
		a.printBigInteger();
		System.out.print(" + ");
		b.printBigInteger();
		System.out.print(" = ");
		c.printBigInteger();
		System.out.println();
	}	

}