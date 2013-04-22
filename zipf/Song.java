package zipfsong;

import java.util.Iterator;

public class Song {

  private String name;
	private double quality;
	private int uniqueID;
	Song right;
	Song left;


	public Song(double quality, String name, int id){
		this.quality = quality;
		this.name = name;
		this.uniqueID = id;
	}

	public int getID() {
		return uniqueID;
	}

	public String getName() {
		return name;
	}

	public double getQuality() {
		return quality;
	}



}
  
