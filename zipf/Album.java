package zipfsong;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Album {

  private int songCount;
	private int songSelect;
	
	List<Song> songList = new ArrayList();
	public Album(int songCount, int songSelect){
		this.songCount = songCount;
		this.songSelect = songSelect;
	}
	
	protected void findSongs(){
		Hashtable h = new Hashtable();
	
		for(int i = 0; i< this.songSelect; i++){
			h.put(songList.get(i), songList.get(i).getQuality());
			System.out.println("Name: "+this.songList.get(i).getName()+" ID: "+this.songList.get(i).getID());
		}

	}
	
	protected void orderSongs(Song s){
		   if( s != null ){
			   orderSongs(s.right);
			   this.songList.add(s);
			   System.out.println(s.getName());
			   orderSongs(s.left);
		   }
	}

	   public void insert(Song n, double d, String name, int id){
		   
		   if(d <= n.getQuality()){
			  if( n.left != null){
				  insert( n.left, d, name, id	);
			  }else{
				  n.left = new Song(d,name, id);
			  }   
		   }else if(d > n.getQuality()){
			   if(n.right != null){
				   insert(n.right, d, name, id);
			   }else{
				   n.right = new Song(d, name, id);
			   }
		   }
	   }
	
}
