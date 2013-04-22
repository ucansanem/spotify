package zipfsong;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zipf {

  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Give me the input!");
		Scanner sc = new Scanner(System.in);
		int f = sc.nextInt();
		int s = sc.nextInt();
		System.out.println();
		Album a = new Album(f, s);
		
		
		//create the root song in the binary search tree  
		int times = sc.nextInt(); int i =1;
		String name = sc.next();
		Song rootSong = new Song(calcQuality(times, i), name, i);

		
		for(i = 2; i<= f ; i++){
			times = sc.nextInt();
			name = sc.next();
			
			
			if(checkName(name)){
				
				a.insert(rootSong, calcQuality(times,i), name, i);
				
			}else{
				System.err.print("Couldn't add the song name! "+name);
			}
			
		}
		
		a.orderSongs(rootSong);
		a.findSongs();
	}

	private static double calcQuality(int times, int line){
		return times * line;
	}
	
	private static boolean checkName(String name) {
		// TODO Auto-generated method stub
		boolean b = true;
		char[] c = name.toCharArray();
		int len = c.length;
		if (name == ""){
			b = false;
		}
		if(len >= 30 || len<= 0){
			b = false;
		}
		Pattern p = Pattern.compile("[a-zA-Z0-9_]*");
		Matcher m = p.matcher(name);
		if(	!m.matches() ){
			b = false;
		}
		return b;	
	}

}
