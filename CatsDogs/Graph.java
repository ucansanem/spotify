package CatsDogs;

public class Graph {
  private String id;
	Node a;
	Node b;
	int weight;
	
	public Graph(Node nc, Node nd, int w) {
		// TODO Auto-generated constructor stub
		this.a = nc;
		this.b = nd;
		this.weight = w;
		this.id = nc.name+nd.name;
	}
	
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Graph)) {
            return false;
        }
        Graph other = (Graph) obj;
        return this.id.equals(other.id);
    }
    
    public int hashCode() {
        return id.hashCode();
    }
}
