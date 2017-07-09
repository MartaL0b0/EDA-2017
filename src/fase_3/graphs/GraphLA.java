package fase_3.graphs;

import java.io.IOException;
import cola.SQueue;
import fase_3.dlist.DListVertex;
import fase_3.dlist.DNodeVertex;
import fase_3.dlistwords.DListWords;


public class GraphLA {

	int numVertices;
	int maxVertices;

	DListVertex[] vertices;
	boolean directed;

	public GraphLA(int n, int max) {
		if (max < 0)
			throw new IllegalArgumentException("Negative maximum number of vertices!!!");
		if (n < 0)
			throw new IllegalArgumentException("Negative number of vertices!!!.");
		if (n > max)
			throw new IllegalArgumentException("number of vertices can never " + "be greater than the maximum.");
		maxVertices = max;
		vertices = new DListVertex[maxVertices];
		numVertices = n;
		// creates each list
		for (int i = 0; i < numVertices; i++) {
			vertices[i] = new DListVertex();
		}
		directed = true;
	}

	public DListWords getWords(int n, String file) throws IOException, NullPointerException {
		SQueue colaux = new SQueue();
		try { // La siguiente linea puede lanzar una excepcion de tipo
				// IOException
			colaux.readFileNoAccent(file);
		} catch (IOException ioex) {
			// Capturamos la excepcion IOException
			System.out.println("File " + file + " not found in " + System.getProperty("user.dir"));
			throw ioex;
		}
		
		// System.out.println(colaux.getSize()); Para comprobar que me lista las palabras bien, las cuento
		
		GameData.listaPalabras.add(colaux);
		DListWords listInicial = new DListWords();
		if (GameData.listaPalabras.size < n) {
			System.out.println("Archivo demasiado corto. No se puede formar un grafo con tantas palabras.");
			return null;
		}else if (GameData.listaPalabras.size == n){
			while(GameData.listaPalabras.size != 0){
				listInicial.addLast(GameData.listaPalabras.getFirst());
				GameData.listaPalabras.removeFirst();
			}
		}else{
			int num1;
			int num2;
			for (int i = 0; i < n; i++) {
				num1 = 0;
				num2 = GameData.listaPalabras.size - 1;
				int x = (int) Math.floor(Math.random() * (num1 - (num2 + 1)) + (num2));
			
				String word = "";
				try { // La siguiente linea puede lanzar una excepcion de tipo
						// IOException
					word = GameData.listaPalabras.getAt(x);
					
				} catch (NullPointerException ioex) {
					// Capturamos la excepcion NullPointerException
					System.out.println("Couldn't get word with index: " + x);
					throw ioex;
				}

				if (word == null || word.length() < 2) {
					i--;
				} else {

					listInicial.addLast(GameData.listaPalabras.getAt(x));
					GameData.listaPalabras.removeAt(x);
				}
				
				}	
			}
		

		
		//System.out.println(listInicial.toString());
		return listInicial;
	}

	public void addVertexWord(String w) {
		if (numVertices == maxVertices) {
			System.out.println("Cannot add new vertices!!!");
			return;
		}
		numVertices++;
		vertices[numVertices - 1] = new DListVertex(w);
	}

	public void addChainings() {
		//Borra todas las adyacencias previas por si hubieran cambiado al cambiar las palabras
		for (int i = 0; i < maxVertices; i++) {
			while (vertices[i].getSize() != 0) {
				vertices[i].removeFirst();
			}
		}
		// recorrer la lista desde el principio, y si coincide que la última es
		// la primera, añade arista
		for (int i = 0; i < maxVertices; i++) {
			for (int j = 0; j < maxVertices; j++) {
				if (vertices[j].word.equals(vertices[i].word)) {
					//System.out.println("Estamos en el mismo vértice.");
				} else {
					if (vertices[i].lsy.equals(vertices[j].fsy)) {
						addEdge(i, j);
					}
				}
			}
		}
	}

	public SQueue sinkFind() {
		SQueue cola = new SQueue();
		for (int i = 0; i < maxVertices; i++) {
			if (getOutDegree(i) == 0 && getInDegree(i) > 0) { // es sumidero, porque entran pero no salen
				System.out.println("La palabra '" + vertices[i].word + "' con índice " + i
						+ " es sumidero. Cuando termine el análisis será reemplazada.");
				cola.enqueue(i);
			}
		}
		return cola;
	}

	public void sinkReplace(SQueue cola) {
		for (int i = 0; i < cola.getSize(); i++) {
			int index = (int) cola.front();
			int num1 = 0;
			int num2 = GameData.listaPalabras.size - 1;
			int x = (int) Math.floor(Math.random() * (num1 - (num2 + 1)) + (num2));
			String word;
			if (GameData.listaPalabras.size > 0) {
				try { // La siguiente linea puede lanzar una excepcion de tipo
						// IOException
					word = GameData.listaPalabras.getAt(x);
				} catch (NullPointerException ioex) {
					// Capturamos la excepcion NullPointerException
					System.out.println("Couldn't get word with index: " + x);
					throw ioex;
				}
				// System.out.println(word);
				if (word == null || word.length() < 2) {
					i--;
				} else {
					index = (int) cola.dequeue();
					vertices[index].word = word;
					// System.out.println("Palabra de la lista '" +
					// vertices[index].word +"' y palabra por la que se ha
					// cambiado '"+ word +"', deben ser iguales.");
					GameData.listaPalabras.removeAt(x);
					// System.out.println(word.length());
					vertices[index].fsy = "" + word.charAt(0) + word.charAt(1);
					vertices[index].lsy = "" + word.charAt(word.length() - 2) + word.charAt(word.length() - 1);
				}
			} else {
				System.out.println("Ya no se pueden sustituir más palabras, la lista está vacía.");
			}
		}
	}

	public void sinkRoutine() {
		System.out.println("Comenzando la búsqueda de palabras sumidero...");
		SQueue colaux = sinkFind();
		if (!colaux.isEmpty()){
			sinkReplace(colaux);
			addChainings();
		} else {
			System.out.println("No hay palabras sumidero");
		}
		
	}

	public SQueue getChainedList(String w) {
		SQueue colaListas = new SQueue();
		int vInic = -1;
		//bucle para buscar el vértice de la palabra que queremos analizar
		for (int i = 0; i < maxVertices; i++) {
			if (vertices[i].word.equals(w)) {
				vInic = i;
				i = maxVertices - 1;
			}
		}
		//Si no hemos encontraado la palabra
		if(vInic == -1){
			System.out.println("La palabra que buscas, '"+w+ "', no está en el grafo.");
			return colaListas;
		}
		//System.out.println("Empezamos en el vértice " + vInic);

		// Comenzamos el proceso de búsqueda
		DListVertex a = new DListVertex();
		a.addFirst(vInic, vertices[vInic].word);
		SQueue colaux = new SQueue();
		colaux.enqueue(a);

		// System.out.println(colaux.toString());
		//mientras haya listas por terminar
		while (!colaux.isEmpty()) {
			//cogemos la primera lista de la cola
			DListVertex a1 = (DListVertex) colaux.dequeue();
			//sacamos el último vértice analizado
			int vertex = a1.getLast();
			//guardamos su lista de adyacentes
			DListVertex adjac = vertices[vertex];
			//imprimimos sus adyacentes (CONTROL)
//			for(int i = 0; i < adjac.size; i++){
//				 System.out.println("vertices adyacentes: " + vertices[adjac.getVertexAt(i)].word);
//			}
			//Si el vértice no tiene adyacentes o la lista ya tiene el tamaño máximo (número de palabras)
			if (adjac.size == 0 || a1.size == GameData.numPalabras) {
				// ya hemos terminado de rellenar esa lista, la guardamos en la
				// cola de las listas terminadas
				colaListas.enqueue(a1);
			} else {
				//creamos un array para las nuevas listas que se van a formar, que son iguales que la desencolada + uno de los vértices adyacentes
				DListVertex [] nuevasLists = new DListVertex[adjac.size];
				//copiamos las listas, varias veces, porque luego seran diferentes
				for(int i =0; i< adjac.size; i++){
					nuevasLists[i] = new DListVertex();
					DNodeVertex nodoa1 = a1.header;
					for(nodoa1 = a1.header.next; nodoa1 != a1.tailer;nodoa1 = nodoa1.next){
						nuevasLists[i].addLast(nodoa1.vertex, vertices[nodoa1.vertex].word);
					}
				}
				
				DNodeVertex nodeAux = adjac.header;
				int i = 0;
				for (nodeAux = adjac.header.next; nodeAux != adjac.tailer; nodeAux = nodeAux.next) {
//					System.out.println("Voy a añadir el ady "+ nodeAux.vertex + " a la lista " + nuevasLists[i].toString());
					if (nuevasLists[i].getIndexOf(nodeAux.vertex) == -1) {
						 nuevasLists[i].addLast(nodeAux.vertex, vertices[nodeAux.vertex].word);
						 nuevasLists[i].toString();
						colaux.enqueue(nuevasLists[i]);
					}else{
						colaListas.enqueue(nuevasLists[i]);
					}
					i++;
				}
			}
		}
		return colaListas;
	}

	public DListVertex getLongest(SQueue cola){
		if(!cola.isEmpty()){
			DListVertex listaMasLarga = new DListVertex();
			SQueue colaLarga = new SQueue();
			SQueue colaDescarte = new SQueue();
			while(!cola.isEmpty()){
				DListVertex listaAnalizar = (DListVertex) cola.dequeue();
				if (colaLarga.isEmpty()){
					colaLarga.enqueue(listaAnalizar);
				} else {
					DListVertex listaComparar = (DListVertex) colaLarga.front();
					if(listaComparar.size > listaAnalizar.size){
						colaDescarte.enqueue(listaAnalizar);
					}else{
						colaLarga.enqueue(listaAnalizar);
						colaDescarte.enqueue(colaLarga.dequeue());
					}
				}
			}
			listaMasLarga = (DListVertex) colaLarga.dequeue();
			return listaMasLarga;
		}else{
			//System.out.println("No se puede buscar la cadena más larga porque no hay listas posibles.");
			return null;
		}
	}
	
	public DListVertex getLongestChainWord (String w){
		SQueue colaListas = getChainedList(w);
		DListVertex longestList = getLongest(colaListas);	
		return longestList;
	}
	
	public DListVertex getLongestChainGame(){
		SQueue listasMasLargas = new SQueue();
		for(int i = 0; i < maxVertices; i++){
			if (getLongestChainWord(vertices[i].word) != null){
				listasMasLargas.enqueue(getLongestChainWord(vertices[i].word));
			}
		}

		
		
		DListVertex longestestList = getLongest(listasMasLargas);
		return longestestList;
	}
	
	public int getOutDegree(int i) {
		if (!checkVertex(i))
			throw new IllegalArgumentException("Nonexistent vertex  " + i);

		int outdegree = 0;
		outdegree = vertices[i].getSize();
		return outdegree;
	}

	public int getInDegree(int i) {
		if (!checkVertex(i))
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		int indegree = 0;
		for (int j = 0; j < numVertices; j++) {
			if (vertices[j].contains(i))
				indegree++;
		}
		return indegree;
	}

	public void addEdge(int i, int j) {
		if (!checkVertex(i))
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j))
			throw new IllegalArgumentException("Nonexistent vertex  " + j);

		vertices[i].addLast(j, vertices[j].word);
		// if it is a non-directed graph
		if (!directed)
			vertices[j].addLast(i, vertices[i].word);
	}

	// check if i is a right vertex
	private boolean checkVertex(int i) {
		if (i >= 0 && i < numVertices)
			return true;
		else
			return false;
	}

	public void show(char e) {
		
		if(numVertices == 0){
			System.out.println("Grafo de " + maxVertices + " posibles vértices vacío.");
		}else {
			if (e == 'e') {
				System.out.println("GRAFO COMPLETO, INCLUYENDO VÉRTICES INCONEXOS");
				for (int i = 0; i < numVertices; i++) {
					if (vertices[i] != null) {
						System.out.println(
								"adjacentes vertices for vertex with word '" + vertices[i].word + " " + vertices[i].fsy
										+ " " + vertices[i].lsy + "' and index " + i + ": " + vertices[i].toString());
					}
				}
			} else {
				System.out.println("GRAFO REPRESENTADO SOLO POR SUS VÉRTICES CONEXOS");
				for (int i = 0; i < numVertices; i++) {
					if (vertices[i] != null && vertices[i].toString() != "empty") {
						System.out.println(
								"adjacentes vertices for vertex with word '" + vertices[i].word + " " + vertices[i].fsy
										+ " " + vertices[i].lsy + "' and index " + i + ": " + vertices[i].toString());
					}
				}
			}
		}
		
		
	}
}
