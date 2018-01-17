public class Helper 
{
	
	public static void main(String[] args) 
	{
		helpQuestion2();
	}


	public static void helpQuestion2() {
		int[][] input = { {574, 460, 107, 374, 871, 421, 513}, {579, 207, 29, 2, 811, 81, 5} };
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < input[i].length; j++)
			{
				System.out.println("hash("+ input[i][j] +") = "+ hash(input[i][j]));
			}
			System.out.println();
		}
	}

	public static int hash(int x)
	{
		return (	( (x/100) % 10 ) + ( (x/10) % 10 ) + (x%10)	) % 17;
	}
}