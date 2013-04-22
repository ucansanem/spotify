import java.util.Scanner;

public class ReverseBit {

  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the number");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		if(i < 1 || i > 1000000000){
			System.out.println("Number should be between 1 and 1000000000");
		}
		else{
			bitConvert(i);
		}
		
	}

	private static void bitConvert(int i) {
		// TODO Auto-generated method stub
		String s = "";
		int div = i;
		while( div != 0 ){
			if( div%2 == 0 ){
				s += "0";
			}else{
				s += "1";
			}
			
			div = div/2;
		}
		
		int res = 0;
		
		char[] ar = s.toCharArray();
		int len = ar.length;
		for(int a = 0; a < len ; a++){

			if(ar[a] == '1'){
				res += Math.pow(2, (len-1-a));
			}
		}
		System.out.println(res);
	}

}

