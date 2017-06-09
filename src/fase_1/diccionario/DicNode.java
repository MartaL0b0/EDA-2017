package fase_1.diccionario;

public class DicNode {

	public String word;
	public int frec;
	public DicNode prev;
	public DicNode next;
	
	public DicNode(String word) {
		this.word = word;
		frec = 1;
	}
	public DicNode(String word, int freq) {
		this.word = word;
		this.frec = freq;
	}

}

