package fase_1.diccionario;



/**
 * A double-linked list class with sentinel nodes
 * BY MARTA LOBO DE PABLOS
 */

public class DList  {

	public DicNode header; // by default is null
	public DicNode tailer;// by default is null
	public int size; // by default is 0

	public DList() { // constructor
		// we have to create the sentinel nodes
		header = new DicNode(null);
		tailer = new DicNode(null);
		// they have to point with each other
		header.next = tailer;
		tailer.prev = header;
	}

	public void addFirst(String elem, int freq) { 
		DicNode newNode = new DicNode(elem, freq);
		newNode.next = header.next;
		newNode.prev = header;
		header.next.prev = newNode;
		header.next = newNode;
		size++;
	}

	public void addLast(String elem, int freq) { 
		DicNode newNode = new DicNode(elem, freq);
		newNode.next = tailer;
		newNode.prev = tailer.prev;
		tailer.prev.next = newNode;
		tailer.prev = newNode;
		size++;

	}
	
	public String toString() { 
		String result = null;
		for (DicNode nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "" + nodeIt.word + " (" + nodeIt.frec + ")";
			} else {
				result += ", " + nodeIt.word+ " (" + nodeIt.frec + ")";
			}
		}
		//lo cambio a mayusculas para diferenciar un toString del otro
		String result2 = result.toUpperCase();
		return result2 == null ? "empty" : result2;
	}

	

	public void removeFirst() { // funciona
		if (size == 0) {
			System.out.println("DList: List is empty");
			return;
		}
		header.next = header.next.next;
		header.next.prev = header;
		size--;
		
	}

	public void removeLast() { // funciona
		if (size == 0) {
			System.out.println("DList: List is empty");
			return;
		}
		tailer.prev = tailer.prev.prev;
		tailer.prev.next = tailer;
		size--;
	}
	
	
	
	public void addFreq(String word, int frec) {
		// System.out.println("Meto *" + word + "* en una lista de tamaño " +
		// size);

		if (size == 0) {
			DicNode nodo = new DicNode(word, frec);
			nodo.next = header.next;
			nodo.prev = header;
			header.next.prev = nodo;
			header.next = nodo;
			size++;
		} else if (size == 1) {
			DicNode nodo = header.next;
			// System.out.println(nodo.word);
			if (nodo.frec < frec) {
				// System.out.println("La palabra que quiero meter está por
				// delante");
				DicNode nodonew = new DicNode(word, frec);
				nodonew.next = header.next;
				nodonew.prev = header;
				header.next.prev = nodonew;
				header.next = nodonew;
				size++;
			} else {
				// System.out.println("La palabra que quiero meter está por
				// detrás");
				DicNode nodonew = new DicNode(word, frec);
				nodonew.next = tailer;
				nodonew.prev = tailer.prev;
				tailer.prev.next = nodonew;
				tailer.prev = nodonew;
				size++;
			}
		} else {
			boolean insert = false;
			for (DicNode nodo = header.next; nodo != tailer && !insert; nodo = nodo.next){
				if (nodo.frec < frec) {
					//tengo que meterlo delante del nodo donde estoy
					DicNode nodonew = new DicNode(word, frec);
					nodonew.next = nodo;
					nodonew.prev = nodo.prev;
					nodonew.prev.next = nodonew;
					nodonew.next.prev = nodonew;

					size++;
					insert = true;
				}
			}
			if(!insert){
				DicNode nodonew = new DicNode(word);
				nodonew.next = tailer;
				nodonew.prev = tailer.prev;
				nodonew.prev.next = nodonew;
				nodonew.next.prev = nodonew;
				size++;
				insert = true;
			}
		}

	}

	public boolean contains(Object elem) { // funciona
		boolean container = false;
		for (DicNode node = header; node != tailer && !container; node = node.next) {
			if (node.word == elem) {
				container = true;
				// node = tailer.prev; esto es lo que sustituiría a la condición
				// && !container
			}

		}
		return container;
	}

	public int getIndexOf(Object elem) { // funciona
		int index = -1;
		int size = getSize();
		DicNode node;

		if (contains(elem)) {
			for (node = header.next; node != tailer && index != size - 1; node = node.next) {
				index++;
				if (node.word == elem) {
					node = tailer.prev;
				}

			}
		}
		return index;
	}


	public int getSize() { // funciona
		int c = 0;
		DicNode aux = header.next;
		while (aux != tailer) {
			c++;
			aux = aux.next;
		}
		return c;
	}

	

}
