package fase_2.dictionarytreefreq;

import fase_1.diccionario.DicNode;
import fase_1.diccionario.DictionaryList;

public class DictionaryTreeFreq implements IDictionaryTreeFreq {

	public BSTFreqNode root;
	public DictionaryTreeFreq() {
		root = null;
	}

	@Override
	public void save(DictionaryList lista) {
		// lista es una lista con palabras y su frecuencia asociada
		DicNode aux = lista.header;
		for (aux = lista.header.next; aux != lista.tailer; aux = aux.next) {
			add(aux.word, aux.frec);
		}
//		for (nodoshow = header.next; nodoshow != tailer; nodoshow = nodoshow.next) {
//			System.out.println(nodoshow.word + "\t" + nodoshow.frec);
//		}
	}

	@Override
	public void add(String word, int freq) {
		BSTFreqNode newNode = new BSTFreqNode(word, freq);
		if (root == null) { // no necesitamos el constructor con nodo
			root = newNode;
		} else {
			add(newNode, root); // llamada al metodo recursivo auxiliar con nodo
		}
	}

	private void add(BSTFreqNode newNode, BSTFreqNode node) {
		if (newNode.key == node.key) { // coincide, no podemos insertar
				node.wordlist.addLast(newNode.wordlist.getLast());
				//System.out.println(newNode.wordlist.getLast());
				//newNode.wordlist.removeLast();
			return;
		}
		if (newNode.key < node.key) { // a la izquierda
			if (node.left == null) { // si no tiene hijo izquierdo, lugar
										// encontrado
				node.left = newNode;
				newNode.parent = node;
			} else
				add(newNode, node.left); // llamada recursiva con hijo izquierdo
		} else { // a la derecha
			if (node.right == null) { // si no tiene hijo derecho, lugar
										// encontrado
				node.right = newNode;
				newNode.parent = node;
			} else
				add(newNode, node.right); // llamada recursiva con hijo derecho
		}
	}
}
	

