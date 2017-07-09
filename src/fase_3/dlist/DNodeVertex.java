package fase_3.dlist;

public class DNodeVertex {

	public Integer vertex;
	public String word;
	
	public DNodeVertex prev;
	public DNodeVertex next;
	
	public DNodeVertex(Integer v, String w) {
		vertex = v;
		word = w;
	}
	public DNodeVertex() {
		
	}

}

