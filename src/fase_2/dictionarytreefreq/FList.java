package fase_2.dictionarytreefreq;


/**
 * A double-linked list class with sentinel nodes
 * BY MARTA LOBO DE PABLOS
 */

public class FList {

	FNode header; // by default is null
	FNode tailer;// by default is null
	int size; // by default is 0

	public FList() { // constructor
		// we have to create the sentinel nodes
		header = new FNode(null);
		tailer = new FNode(null);
		// they have to point with each other
		header.next = tailer;
		tailer.prev = header;
	}

	public void addFirst(String elem) { // funciona
		FNode newNode = new FNode(elem);
		newNode.next = header.next;
		newNode.prev = header;
		header.next.prev = newNode;
		header.next = newNode;
		size++;
	}

	public void addLast(String elem) { // funciona
		FNode newNode = new FNode(elem);
		newNode.next = tailer;
		newNode.prev = tailer.prev;
		tailer.prev.next = newNode;
		tailer.prev = newNode;
		size++;

	}

	public void removeFirst() { // funciona
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		header.next = header.next.next;
		header.next.prev = header;
		size--;
	}

	public void removeLast() { // funciona
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		tailer.prev = tailer.prev.prev;
		tailer.prev.next = tailer;
		size--;
	}

	// laboratorio
	public void insertAt(int index, String elem) { // lo tiene alex y funciona

		FNode aux = header;
		FNode node = new FNode(elem);

		if (index > getSize()) {
			System.out.println("La lista es demasiado pequeña.");
		}

		for (int i = 0; i < index; i++) {
			aux = aux.next;
		}

		node.next = aux.next;
		node.next.prev = node;
		aux.next = node;
		node.prev = aux;
		size++;

	}

	public boolean isEmpty() { // funciona
		return header.next == tailer;
	}

	public boolean contains(String elem) { // funciona
		boolean container = false;
		for (FNode node = header; node != tailer && !container; node = node.next) {
			if (node.word.equals(elem)) {
				container = true;
				// node = tailer.prev; esto es lo que sustituiría a la condición
				// && !container
			}

		}
		return container;
	}

	public int getIndexOf(String elem) { // funciona
		int index = -1;
		int size = getSize();
		FNode node;

		if (contains(elem)) {
			for (node = header.next; node != tailer && index != size - 1; node = node.next) {
				index++;
				if (node.word.equals(elem)) {
					node = tailer.prev;
				}

			}
		}
		return index;
	}

	public void removeAll(String elem) {

		if (!contains(elem)) {
			System.out.println("El elemento no se encuentra en la lista.");
		} else {
			removeAt(getIndexOf(elem));
		}

	}

	public void removeAt(int index) { // funciona
		FNode aux = header;

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
		FNode aux = header.next;
		while (aux != tailer) {
			c++;
			aux = aux.next;
		}
		return c;
	}

	public String getFirst() { // funciona
		return  header.next.word;
	}

	public String getLast() { // funciona
		return  tailer.prev.word;
	}

	public String getAt(int index) { // funciona
		FNode aux = header;

		if (index > getSize()) {
			System.out.println("La lista es demasiado pequeña.");
		}

		for (int i = 0; i <= index; i++) {
			aux = aux.next;
		}

		return aux.word;
	}

	public String toString() { // funciona
		String result = null;
		for (FNode nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "" + nodeIt.word;
			} else {
				result += ", " + nodeIt.word;
			}
		}
		return result == null ? "empty" : result;
	}

	
	
	

}
