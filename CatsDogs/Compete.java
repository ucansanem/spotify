/*
2  ==> test case number , at most 100

1 1 2
| |	|
| |	 ==> 0 <= number of voters <= 500 
| |====> 1 <= number of dogs <= 100
|======> 1 <= number of cats <= 100
  	
C1 D1  => C/D identifier of a Cat or a Dog
 |  |===> to be thrown out
 |======> to be kept
D1 C1	

1 2 4
	|
	 ==> 4 lines
	 
C1 D1	--> 1
C1 D1	--> 2
C1 D2	--> 3
D2 C1   --> 4

 */


package CatsDogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compete {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Give me the input!");
		Scanner sc = new Scanner(System.in);
		int scenario = sc.nextInt();
		System.out.println();
		for(int i = 0 ; i < scenario ; i++){
			int cats = sc.nextInt();
			int dogs = sc.nextInt();
			int votes = sc.nextInt();
			Case c = new Case(cats, dogs, votes, i+1);
			List<String> vLines = new ArrayList();
			if(votes == 0 ){
				System.err.println("No votes for Case "+(i+1)+" !");
			}else if (votes > 0 && votes <=500){
				for(int j = 0 ; j < votes; j++){
					vLines.add(sc.next()+" "+sc.next());
				}
				c.addVotes(vLines);
				c.analyzeVotes();
			}else{
				System.err.println("There are more than 500 votes for Case "+(i+1)+" !");
			}
		}
	}

}
