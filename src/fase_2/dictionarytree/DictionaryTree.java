package fase_2.dictionarytree;

import cola.SQueue;
import fase_1.diccionario.DList;




public class DictionaryTree implements IDictionaryTree {

	static BSTNode root;
	
	//constructores
	public DictionaryTree(){
		
	}
	
	public DictionaryTree(BSTNode raiz){
		root = raiz;
	}
	
	//metodos pedidos en la practica
	@Override
	public void add(SQueue cola) {	//T(n) = 11n + 22 + recursivo
		while (!cola.isEmpty()) {
			add((String)cola.dequeue());
			/* Imprimir la lista como control System.out.println(toString());*/
		}
	}

	@Override
	public void add(String word) {	// T(n) = 3 + recursivo
		//esto es el insert de la clase BSTree
		//primero habra que buscar si la palabra existe, y entonces cambiar la frecuencia
		//si no habra que insertarlo.
		BSTNode newNode = new BSTNode(word, 1);
		if (root == null){
			root = newNode;
		} else add(newNode, root);
	}

	//este es el metodo recursivo añadido a posteriori	
	private void add(BSTNode newNode, BSTNode node){	//Método recursivo
		String key = newNode.key;
		if (key.equals(node.key)){
			//System.out.println(key + " already exists. Aumentando frecuencias...");
			node.frec++;
			return;
		}
		if (key.compareTo(node.key) < 0){
			if (node.left ==null){
				node.left = newNode;
				newNode.parent = node;
			} else {
				add(newNode, node.left);
			}
		} else {
			if (node.right == null){
				node.right = newNode;
				newNode.parent = node;
			} else{
				add(newNode, node.right);
			}
		}
	}
	
		
	
	@Override
	public void show(char a) {	// T(n) = 1 + recursivo
		if (a == 'A') {
			System.out.println("Mostrando el árbol en orden alfabético de la A a la Z.");
			getInorder(root);	//funciona
		} else {
			System.out.println("Mostrando el árbol en orden alfabético de la Z a la A.");
			getIndesorder(root);	//no funciona
		}
	}
	
	//metodos para mostrar el árbol
	private static void getInorder(BSTNode node) {	//Método recursivo
		if (node == null) return;
		getInorder(node.left);
		System.out.println("("+node.key+", "+node.frec+")");
		getInorder(node.right);
	}
	
	private static  void getIndesorder(BSTNode node) {	//Método recursivo
		if (node == null) return;
		getIndesorder(node.right);
		System.out.println("("+node.key+", "+node.frec+")");
		getIndesorder(node.left);
	}
	
	
	@Override
	public int search(String word) {	//T(n) = 1 + 4 log2 n ; O(n) = casi lineal
		BSTNode busca = root;
		while (busca != null){
			String keyBusca = busca.key;
			if (word.equals(keyBusca)){
				return busca.frec;
			} else if (word.compareTo(keyBusca) <0){
				busca = busca.left;
			} else {
				busca = busca.right;
			}
		}
		System.out.println(word + " does not exist in the dictionary");
		return -1;
	}
	

	@Override
	public DList getTop(int n) {	//T(n)= 2n + 7 + llamada a recursivo
		DList listaFreq = new DList();
		int listaTamMax = n;
		listaFreq = freqOrder(root, listaFreq);
		//recorro el arbol y guardo en una lista de tamaÃ±o n las palabras con mayores frecuencias
		int listaTam = listaFreq.size;
		for(int i = listaTam ; i > listaTamMax; i--){
			listaFreq.removeLast();
		}
		return listaFreq;
	}

	private static DList freqOrder(BSTNode node, DList lista){	//método recursivo
		if (node == null) return lista;
		freqOrder(node.left, lista);
		lista.addFreq(node.key, node.frec);;
		freqOrder(node.right, lista);
		return lista;
	}
	
	@Override
	public DList getLow(int n) {	//T(n)= 2n + 7 + llamada a recursivo
		DList listaFreq = new DList();
		int listaTamMax = n;
		listaFreq = freqOrder(root, listaFreq);
		//recorro el arbol y guardo en una lista de tamaÃ±o n las palabras con mayores frecuencias
		int listaTam = listaFreq.size;
		for(int i = listaTam ; i > listaTamMax; i--){
			listaFreq.removeFirst();
		}
		return listaFreq;
	}
	
}