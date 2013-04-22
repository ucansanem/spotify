package CatsDogs;

import java.util.Comparator;

public class MyGraphComparable implements Comparator<Graph> {

  @Override
	public int compare(Graph arg0, Graph arg1) {
		// TODO Auto-generated method stub
		return arg0.weight - arg1.weight;
	}
}
