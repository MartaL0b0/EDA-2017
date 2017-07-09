package fase_3.dlist;


/**
 * A double-linked list class with sentinel nodes
 */

public class DListVertex implements IList {

	public DNodeVertex header;
	public DNodeVertex tailer;
	public int size=0;
	public String word;
	public String fsy;
	public String lsy;

	public DListVertex() {
		header = new DNodeVertex(null,null);
		tailer = new DNodeVertex(null,null);
		header.next = tailer;
		tailer.prev= header;
	}
	
	public DListVertex(String w) {
		header = new DNodeVertex(null,null);
		tailer = new DNodeVertex(null,null);
		header.next = tailer;
		tailer.prev= header;
		word = w;
		if (word.length() > 1){
			fsy = "" + w.charAt(0) + w.charAt(1);
			lsy = "" + w.charAt(word.length()-2) + w.charAt(w.length()-1); 
		}
		
	}
	
	public void addFirst(int v, String w) {
		DNodeVertex newNode = new DNodeVertex(v,w);
		newNode.next = header.next;
		newNode.prev= header;
		header.next.prev= newNode;
		header.next = newNode;
		size++;
	}

	
	public void addLast(int v, String w) {
		DNodeVertex newNode = new DNodeVertex(v,w);
		newNode.next = tailer;
		newNode.prev= tailer.prev;
		tailer.prev.next = newNode;
		tailer.prev= newNode;
		size++;
	}

	
	public void insertAt(int index, int v, String w) {
		DNodeVertex newNode = new DNodeVertex(v,w);
		int i = 0;
		boolean inserted=false;
		for (DNodeVertex nodeIt = header; nodeIt != tailer && inserted==false; nodeIt = nodeIt.next) {
			if (i == index) {
				newNode.next = nodeIt.next;
				newNode.prev= nodeIt;
				nodeIt.next.prev= newNode;
				nodeIt.next = newNode;
				inserted=true;
				size++;
			}
			++i;
		}
		if (!inserted) System.out.println("DList: Insertion out of bounds");
	}

	

	
	public boolean isEmpty() {
		return (header.next == tailer);
	}

	
	public boolean contains(int vertex) {
		boolean found=false;
		for (DNodeVertex nodeIt = header.next; nodeIt != tailer && found==false; nodeIt = nodeIt.next) {
			if (nodeIt.vertex.equals(vertex)) {
				found=true;
			}
		}
		return found;
	}

	
	public int getIndexOf(int vertex) {
		int index = -1;
		int pos=0;
		for (DNodeVertex nodeIt = header.next; nodeIt != tailer && index==-1; nodeIt = nodeIt.next) {
			if (nodeIt.vertex.equals(vertex)) {
				index=pos;
			} 
			++pos;
			
		}
		return index;
	}

	
	public void removeFirst() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		header.next = header.next.next;
		header.next.prev= header;
		size--;
	}

	
	public void removeLast() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		tailer.prev= tailer.prev.prev;
		tailer.prev.next = tailer;
		size--;

	}

	
	public void removeAll(int vertex) {
		for (DNodeVertex nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (nodeIt.vertex.equals(vertex)) {
				nodeIt.prev.next = nodeIt.next;
				nodeIt.next.prev= nodeIt.prev;
				size--;

			}
		}
	}

	
	
	public void removeAt(int index) {
		int i = 0;
		boolean removed=false;
		for (DNodeVertex nodeIt = header.next; nodeIt != tailer && removed==false; nodeIt = nodeIt.next) {
			if (i == index) {
				nodeIt.prev.next = nodeIt.next;
				nodeIt.next.prev= nodeIt.prev;
				removed=true;
				size--;

			}
			++i;
		}
		if (!removed) System.out.println("DList: Deletion out of bounds");
	}

	
	public int getSize() {
		
		return size;
	}

	
	public Integer getFirst() {
		Integer result=null;
		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else result=header.next.vertex;
		return result;
	}

	public Integer getLast() {
		Integer result=null;

		if (isEmpty()) {
			System.out.println("DList: List is empty");
		} else result=tailer.prev.vertex;
		
		return result;
	}

	
	public Integer getVertexAt(int index) {
		int i = 0;
		Integer result=null;
		for (DNodeVertex nodeIt = header.next; nodeIt != tailer && result==null; nodeIt = nodeIt.next) {
			if (i == index) {
				result=nodeIt.vertex;
			}
			++i;
		}
		if (result==null) System.out.println("DList: Get out of bounds");
		return result;
	}

	
	public String toString() {
		String result = null;
		for (DNodeVertex nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = String.valueOf(nodeIt.vertex) +":"+String.valueOf(nodeIt.word);
			} else {
				result += "," + String.valueOf(nodeIt.vertex) +":"+String.valueOf(nodeIt.word);
			}
		}
		return result == null ? "empty" : result;
	}


}
