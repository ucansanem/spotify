package CatsDogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Case {

  int c, d, v, id;
	List<String> voteList;
	List<Node> catList;
	List<Node> dogList;
	
	Hashtable catTable;
	Hashtable dogTable;
	Hashtable voteTable;
	
	Graph g;
	List<Graph> graphList;
		
	public Case(int cats, int dogs, int votes, int id) {
		this.id = id;
		this.c = cats;
		this.d = dogs;
		this.v = votes;
		this.voteList = new ArrayList();
		createCatsDogs();
	}
	
	private void createCatsDogs() {
		this.catList = new ArrayList();
		this.dogList = new ArrayList();
		// TODO Auto-generated method stub
		for(int i = 0 ; i < this.c ; i++){
			Node n = new Node("C"+(i+1));
			catList.add(n);
			
		}
		for(int i = 0 ; i < this.d ; i++){
			Node n = new Node("D"+(i+1));
			dogList.add(n);
		}
		
	}

	protected void addVotes(List<String> votes){
		this.voteList = votes;
	}
	
	void analyzeVotes(){
		Integer j;
		this.catTable = new Hashtable();
		this.dogTable = new Hashtable();
		for(String vote:voteList){
			String[] vo = vote.split(" ");
			 
			if(vo[0].charAt(0) == 'C'){
				j = (Integer)this.catTable.get(vo[0]);
				if(j == null){
					this.catTable.put(vo[0], new Integer(1));
				}else{
					this.catTable.put(vo[0], j.intValue()+1);
				}
			}else if(vo[0].charAt(0) == 'D'){
				j = (Integer)this.dogTable.get(vo[0]);
				if(j == null){
					this.dogTable.put(vo[0], new Integer(1));
				}else{
					this.dogTable.put(vo[0], j.intValue()+1);
				}
			}
			if(vo[1].charAt(0) == 'C'){
				j = (Integer)this.catTable.get(vo[1]);
				if(j == null){
					this.catTable.put(vo[1], new Integer(-1));
				}else{
					this.catTable.put(vo[1], j.intValue()-1);
				}
			}else if(vo[1].charAt(0) == 'D'){
				j = (Integer)this.dogTable.get(vo[1]);
				if(j == null){
					this.dogTable.put(vo[1], new Integer(-1));
				}else{
					this.dogTable.put(vo[1], j.intValue()-1);
				}
			}
		}
		addVotestoTable();
		makeConnections();
	}

	private void addVotestoTable() {
		voteTable = new Hashtable();
		// TODO Auto-generated method stub
		Integer j;
		for(String s: voteList){
			j = (Integer)this.voteTable.get(s);
			if(j == null){
				this.voteTable.put(s, new Integer(1));
			}else{
				this.voteTable.put(s, j.intValue()+1);
			}
		}
	}

	private void makeConnections() {
		// TODO Auto-generated method stub
		graphList = new ArrayList();
		for(Node nc:catList){
			for(Node nd:dogList){
				int cat_key = ((Integer) catTable.get(nc.name)).intValue(); 
				int dog_key = ((Integer) dogTable.get(nd.name)).intValue(); 

				if(cat_key > 0){
					if(dog_key <= 0){
						int w = cat_key + Math.abs(dog_key);
						g = new Graph(nc, nd, w);
						if(!graphList.contains(g) && voteList.contains(nc.name+" "+nd.name)){
							graphList.add(g);
						}					}
				}else if(cat_key < 0){
					if(dog_key >= 0){
						int w = dog_key + Math.abs(cat_key);
						g = new Graph(nd, nc, w);
						if(!graphList.contains(g) && voteList.contains(nd.name+" "+nc.name)){
							graphList.add(g);
						}					}
				}else{
					if(dog_key < 0){
						int w = cat_key + Math.abs(dog_key);
						g = new Graph(nc, nd, w);
						if(!graphList.contains(g) && voteList.contains(nc.name+" "+nd.name)){
							graphList.add(g);
						}
						}else if(dog_key > 0){
						int w = dog_key + cat_key;
						g = new Graph(nd, nc, w);
						if(!graphList.contains(g) && voteList.contains(nd.name+" "+nc.name)){
							graphList.add(g);
						}					
					}else{
						
						g = new Graph(nc,nd, 0);
						if(!graphList.contains(g) && voteList.contains(nc.name+" "+nd.name)){
								graphList.add(g);
						}						
					}
				}
			}
		}
		for(Node nd:dogList){
			for(Node nc:catList){
				int cat_key = ((Integer) catTable.get(nc.name)).intValue(); 
				int dog_key = ((Integer) dogTable.get(nd.name)).intValue(); 

				if(dog_key > 0){
					if(cat_key <= 0){
						int w = dog_key + Math.abs(cat_key);
						g = new Graph(nd, nc, w);
						if(!graphList.contains(g) && voteList.contains(nd.name+" "+nc.name)){
							graphList.add(g);
						}					}
				}else if(dog_key < 0){
					if(cat_key >= 0){
						int w = cat_key + Math.abs(dog_key);
						g = new Graph(nc, nd, w);
						if(!graphList.contains(g) && voteList.contains(nc.name+" "+nd.name)){
							graphList.add(g);
						}
					}
				}else{
					if(cat_key < 0){
						int w = dog_key + Math.abs(cat_key);
						g = new Graph(nd, nc, w);
						if(!graphList.contains(g) && voteList.contains(nd.name+" "+nc.name)){
							graphList.add(g);
						}
					}else if(cat_key > 0){
						int w = dog_key + cat_key;
						g = new Graph(nc, nd, w);
						if(!graphList.contains(g) && voteList.contains(nc.name+" "+nd.name)){
							graphList.add(g);
						}
					}else{
						g = new Graph(nd,nc, 0);
						if(!graphList.contains(g) && voteList.contains(nd.name+" "+nc.name)){
							graphList.add(g);
						}
						
					}
				}
			}
		}
		sortGraph();
		chooseGraphs();
	}
	

	private void chooseGraphs() {
		// TODO Auto-generated method stub
		int counter = 0, size = graphList.size();
		while(size > 0){
			String a = graphList.get(size-1).a.name;
			String b = graphList.get(size-1).b.name;
			String str = a+" "+b;
			counter += ((Integer) voteTable.get(str)).intValue();
			graphList.remove(size-1);
			List<Integer> ind = new ArrayList();
			for(Graph gs: graphList){
				if(gs.a.name.equals(b) || gs.b.name.equals(a)){
					ind.add(graphList.indexOf(gs));
				}
			}
			
			for(Integer in:ind){
				graphList.remove(in.intValue());
			}
			
			size = graphList.size();
		}
		
		System.out.println("Case "+this.id+ " --> "+counter);
	}

	private void sortGraph(){
		Collections.sort(graphList, new MyGraphComparable());
	}

	private void printGraph() {
		// TODO Auto-generated method stub
		for(Graph g:graphList){
			System.out.println(g.a.name+" <-> "+g.b.name+" ---> "+g.weight);
		}
	}
	
}
