package fase_1.diccionario;

import cola.SQueue;

/**
 * A double-linked list class with sentinel nodes
 */

public class DictionaryList implements IList {

	public DicNode header;
	public DicNode tailer;
	public int size;

	public DictionaryList() {
		header = new DicNode(null);
		tailer = new DicNode(null);
		header.frec = 0;
		tailer.frec = 0;

		header.next = tailer;
		tailer.prev = header;
		size = 0;
	}

	@Override
	/*
	 * recibe una cola de palabras y añade cada palabra de la cola al
	 * diccionario, utilizando el siguiente método add.
	 */
	public void add(SQueue cola) {	//T(n) = 11n^2 + 11n + 4 ; O(n) = cuadrático
		while (!cola.isEmpty()) {
			add((String)cola.dequeue());
			/* Imprimir la lista como control System.out.println(toString()); */
		}
	}

	@Override
	/*
	 * recibe una palabra y añade ésta al diccionario en orden alfabético
	 * ascendente (A-Z). Si la palabra ya existe en el diccionario, simplemente
	 * incrementará su frecuencia. Si, por el contrario, la palabra no existe,
	 * debe ser insertada en el lugar que le corresponda dentro del diccionario.
	 * En este caso, la frecuencia de la palabra será 1.
	 */
	public void add(String word) {	//T(n) = 11n + 13 ; O(n) = lineal
		// System.out.println("Meto *" + word + "* en una lista de tamaño " + size);
		if (size == 0) {
			DicNode nodo = new DicNode(word);
			nodo.next = header.next;
			nodo.prev = header;
			header.next.prev = nodo;
			header.next = nodo;
			size++;
		} else if (size == 1) {
			DicNode nodo = header.next;
			// System.out.println(nodo.word);
			if (nodo.word.compareTo(word) > 0) {
				// System.out.println("La palabra que quiero meter está por delante");
				DicNode nodonew = new DicNode(word);
				nodonew.next = header.next;
				nodonew.prev = header;
				header.next.prev = nodonew;
				header.next = nodonew;
				size++;
			} else if (nodo.word.compareTo(word) == 0) {
				// System.out.println("La palabra que quiero meter está ya");
				nodo.frec++;
			} else {
				// System.out.println("La palabra que quiero meter está por detrás");
				DicNode nodonew = new DicNode(word);
				nodonew.next = tailer;
				nodonew.prev = tailer.prev;
				tailer.prev.next = nodonew;
				tailer.prev = nodonew;
				size++;
			}
		} else {
			boolean insert = false;
			for (DicNode nodo = header.next; nodo.next != tailer && !insert; nodo = nodo.next) {
				if (nodo.word.compareTo(word) == 0) {
					// System.out.println("La palabra que quiero meter está ya");
					nodo.frec++;
					insert = true;
				} else if (nodo.word.compareTo(word) > 0) {
					DicNode nodonew = new DicNode(word);
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

	@Override
	/*
	 * recibe un carácter c. Si c es ‘A’, el método deberá mostrar el
	 * diccionario en orden alfabético ascendente. Si el carácter es cualquier
	 * otro, el diccionario se mostrará en orden alfabético descendente. Por
	 * cada palabra, además se deberá mostrar su frecuencia.
	 */
	public void show(char c) {	//T(n) = 4n + 3 ; O(n) = lineal
		DicNode nodoshow = header;
		if (c == 'A') {
			for (nodoshow = header.next; nodoshow != tailer; nodoshow = nodoshow.next) {
				System.out.println(nodoshow.word + "\t" + nodoshow.frec);
			}
		} else {
			for (nodoshow = tailer.prev; nodoshow != header; nodoshow = nodoshow.prev) {
				System.out.println(nodoshow.word + "\t" + nodoshow.frec);
			}
		}
	}

	@Override
	/*
	 * recibe una palabra y devuelve su frecuencia asociada en el diccionario.
	 */
	public int search(String word) {	//T(n) = 4n + 5 ; O(n) = lineal
		DicNode nodosearch = header;
		boolean found = false;
		for (nodosearch = header.next; nodosearch != tailer && !found; nodosearch = nodosearch.next) {
			if (nodosearch.word.equals(word)) {
				found = true;
			}
		}
		return nodosearch.prev.frec; // porque se mueve el nodo al terminar la
										// vuelta, y ya no sigue
	}

	/*
	 * recibe un número entero n y devuelve una lista con las n palabras con
	 * mayor frecuencia del diccionario. La lista debe estar ordenada por
	 * frecuencia. En caso de empate, es decir, palabras con la misma
	 * frecuencia, éstas deberán aparecer por orden alfabético.
	 */
	@Override
	public DList getTop(int n) {	//T(n) = 5n + 5 ; O(n) = lineal
		if (n > size) {
			System.out.println("Hay menos elementos en el diccionario, así que truncaremos la lista resultante al tamaño.");
			n = size;
		}
		DList listaTop = new DList();
		listaTop = frecOrder(listaTop);
		int i = 0;
		for (i = listaTop.size; i>n; i--) {
			listaTop.removeLast();
		}
		return listaTop;
	}

	@Override
	public DList getLow(int n) {	//T(n) = 5n + 5 ; O(n) = lineal
		if (n > size) {
			System.out.println("Hay menos elementos en el diccionario, así que truncaremos la lista resultante al tamaño.");
			n = size;
		}
		DList listaLow = new DList();
		listaLow = frecOrder(listaLow);	
		//System.out.println(listaLow.size);
		for (int i = listaLow.size; i>n; i--) {
			listaLow.removeFirst();	
		}
		return listaLow;
	}
	// Aquí añado el método que necesito para implementar getTop y getLow
	public DList frecOrder(DList lista){
		for (DicNode nodoshow = header.next; nodoshow != tailer; nodoshow = nodoshow.next) {
			lista.addFreq(nodoshow.word, nodoshow.frec);
		}
		return lista;
	}
	
	
	public void removeFirst() {
		if (size==0) {
			System.out.println("DList: List is empty");
			return;
		}
		header.next = header.next.next;
		header.next.prev= header;
		size--;
	}

	public String getFirst() {
		String result="";
		if (size == 0) {
			System.out.println("DList: List is empty");
		} else result=header.next.word;
		return result;
	}
	
	public void removeAt(int index) { // funciona
		DicNode aux = header;
		if (index > size) {
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

	public String getAt(int index) { // funciona
		DicNode aux = header;
		if (index > size) {
			System.out.println("La lista es demasiado pequeña.");
		}
		for (int i = 0; i <= index; i++) {
			aux = aux.next;
		}
		return (String) aux.word;
	}

	public String toString() {
		String result = null;
		for (DicNode nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = nodeIt.word;
			} else {
				result += ", " + nodeIt.word;
			}
		}
		return result == null ? "empty" : result;
	}
}
