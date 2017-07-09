package fase_3.dlistwords;

/**
 * A double-linked list class with sentinel nodes
 * BY MARTA LOBO DE PABLOS
 */

public class DListWords  {

	public DNodeWord header; // by default is null
	public DNodeWord tailer;// by default is null
	public int size; // by default is 0

	public DListWords() { // constructor
		// we have to create the sentinel nodes
		header = new DNodeWord(null);
		tailer = new DNodeWord(null);
		// they have to point with each other
		header.next = tailer;
		tailer.prev = header;
	}

	

	
	
	public void addFirst(String elem) { 
		DNodeWord newNode = new DNodeWord(elem);
		newNode.next = header.next;
		newNode.prev = header;
		header.next.prev = newNode;
		header.next = newNode;
		size++;
	}

	public void addLast(String elem) { 
		DNodeWord newNode = new DNodeWord(elem);
		newNode.next = tailer;
		newNode.prev = tailer.prev;
		tailer.prev.next = newNode;
		tailer.prev = newNode;
		size++;

	}
	
	public String toString() { 
		String result = null;
		for (DNodeWord nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "" + nodeIt.word;
			} else {
				result += ", " + nodeIt.word;
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
	
	
	
//	public void addFreq(String word, int frec) {
//		// System.out.println("Meto *" + word + "* en una lista de tamaño " +
//		// size);
//
//		if (size == 0) {
//			DNodeWord nodo = new DNodeWord(word, frec);
//			nodo.next = header.next;
//			nodo.prev = header;
//			header.next.prev = nodo;
//			header.next = nodo;
//			size++;
//		} else if (size == 1) {
//			DNodeWord nodo = header.next;
//			// System.out.println(nodo.word);
//			if (nodo.frec < frec) {
//				// System.out.println("La palabra que quiero meter está por
//				// delante");
//				DNodeWord nodonew = new DNodeWord(word, frec);
//				nodonew.next = header.next;
//				nodonew.prev = header;
//				header.next.prev = nodonew;
//				header.next = nodonew;
//				size++;
//			} else {
//				// System.out.println("La palabra que quiero meter está por
//				// detrás");
//				DNodeWord nodonew = new DNodeWord(word, frec);
//				nodonew.next = tailer;
//				nodonew.prev = tailer.prev;
//				tailer.prev.next = nodonew;
//				tailer.prev = nodonew;
//				size++;
//			}
//		} else {
//			boolean insert = false;
//			for (DNodeWord nodo = header.next; nodo != tailer && !insert; nodo = nodo.next){
//				if (nodo.frec < frec) {
//					//tengo que meterlo delante del nodo donde estoy
//					DNodeWord nodonew = new DNodeWord(word, frec);
//					nodonew.next = nodo;
//					nodonew.prev = nodo.prev;
//					nodonew.prev.next = nodonew;
//					nodonew.next.prev = nodonew;
//
//					size++;
//					insert = true;
//				}else{
//					if(nodo.frec == frec){
//						DNodeWord nodonew = new DNodeWord(word, frec);
//						nodonew.next = nodo;
//						nodonew.prev = nodo.prev;
//						nodonew.prev.next = nodonew;
//						nodonew.next.prev = nodonew;
//
//						size++;
//						insert = true;
//					}
//				}
//			}
//		}
//
//	}
//
//	// laboratorio
//	public void insertAt(int index, String elem) { // lo tiene alex y funciona
//
//		DNodeWord aux = header;
//		DNodeWord node = new DNodeWord(elem);
//
//		if (index > getSize()) {
//			System.out.println("La lista es demasiado pequeña.");
//		}
//
//		for (int i = 0; i < index; i++) {
//			aux = aux.next;
//		}
//
//		node.next = aux.next;
//		node.next.prev = node;
//		aux.next = node;
//		node.prev = aux;
//		size++;
//
//	}
//
//	public boolean isEmpty() { // funciona
//		return header.next == tailer;
//	}
//
	public boolean contains(Object elem) { // funciona
		boolean container = false;
		for (DNodeWord node = header; node != tailer && !container; node = node.next) {
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
		DNodeWord node;

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

	public void removeAll(Object elem) {

		if (!contains(elem)) {
			System.out.println("El elemento no se encuentra en la lista.");
		} else {
			removeAt(getIndexOf(elem));
		}

	}

	public void removeAt(int index) { // funciona
		DNodeWord aux = header;

		if (index > getSize()) {
			System.out.println("La lista es demasiado pequeña.");
		} else {
			for (int i = 0; i < index; i++) {
				aux = aux.next;
			}

			aux.next = aux.next.next;
			aux.next.prev = aux;
			size--;
		}

	}

	public int getSize() { // funciona
		int c = 0;
		DNodeWord aux = header.next;
		while (aux != tailer) {
			c++;
			aux = aux.next;
		}
		return c;
	}

	

//	public String getFirst() { // funciona
//		return (String) header.next.word;
//	}
//
//	public String getLast() { // funciona
//		return (String) tailer.prev.word;
//	}
//
	public String getAt(int index) { // funciona
		DNodeWord aux = header;

		if (index > getSize()) {
			System.out.println("La lista es demasiado pequeña.");
		}

		for (int i = 0; i <= index; i++) {
			aux = aux.next;
		}

		return (String) aux.word;
	}

	

}
