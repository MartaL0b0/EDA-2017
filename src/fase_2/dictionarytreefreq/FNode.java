package fase_2.dictionarytreefreq;



public class FNode  {
	public String word;
	public FNode next;
	public FNode prev;
	
	
	public FNode (String e){
		word=e;
		next=null;
		prev=null;
		
	}

	
	public FNode getNext() {
		// TODO Auto-generated method stub
		return next;
	}

	
	public FNode getPrev() {
		// TODO Auto-generated method stub
		return prev;
	}

	
	public String getWord() {
		// TODO Auto-generated method stub
		return word;
	}

	
	
}

