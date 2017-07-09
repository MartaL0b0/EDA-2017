package fase_3;

import java.io.IOException;

import cola.SQueue;
import fase_3.dlist.DListVertex;
import fase_3.dlistwords.DListWords;
import fase_3.graphs.GameData;
import fase_3.graphs.GraphLA;



public class Main_Fase3 {

	public static void prueba1(int n) throws NullPointerException, IOException{
		//1. Leemos el fichero y cargamos las palabras

		 	
		GameData.numPalabras = n; //número de palabras con las que vamos a jugar
		String file = "muy_muy_peque.txt";	
		GraphLA grafo = new GraphLA(0, n);	
		GameData.graph = grafo;
		DListWords listaInic = grafo.getWords(n, file);
		
		if(listaInic != null){
			//System.out.println(listaInic.toString());
			grafo.show('e');
			System.out.println();
			//2. Creamos el grafo con esas palabras
			for (int i = 0; i< n; i++){
				grafo.addVertexWord(listaInic.getAt(i));
			}
			grafo.addChainings();
			grafo.show('e');
			System.out.println();
			
			//3. Cambiamos las palabras sumidero que podamos
			grafo.sinkRoutine();
			System.out.println();
			grafo.show('e');
			System.out.println();
			grafo.show('f');
		
			System.out.println();
			
			//4. Mostrar listas de recorrido de palabras encadenadas de algunas palabras
			String wtf = "rosa";
			SQueue cola = grafo.getChainedList(wtf);
						
			//esto era para mostrar todos los posibles caminos desde una palabra
//					SQueue cola2 = new SQueue();
//					if(cola != null){
//						System.out.println("Ahora imprimo las listas resultantes, que van a ser " + cola.getSize());
//						
//						while(!cola.isEmpty()){
//							DListVertex a = (DListVertex) cola.dequeue();
//							System.out.println("Lista " + a.toString());
//							cola2.enqueue(a);
//						}
//					}
			
			if(!cola.isEmpty()){
				System.out.println("La lista más larga que empieza por la palabra '" + wtf +"' es " +grafo.getLongest(cola).toString());
			}else{
				System.out.println("No hay listas posibles que se puedan formar y que partan de la palabra '" + wtf + "'.");
			}
			
			//5. Devolver la lista de palabras encadenadas más larga
			System.out.println();
			DListVertex listaMasLarga = grafo.getLongestChainGame();
			System.out.println("La cadena más larga que se puede formar es: " + listaMasLarga.toString() +", de longitud: "+ listaMasLarga.size);
			
		}else{
			System.out.println("La lista de palabras inicial es null");
		}

		
		System.out.println("\nPruebas de grafo con palabras encadenadas realizadas.");
		
	}
	
	public static void prueba2(int n) throws NullPointerException, IOException{
			
		GameData.numPalabras = n;	//número de palabras con las que vamos a jugar
		String file = "quijoteCorto.txt";	
		GraphLA grafo = new GraphLA(0, n);	
		GameData.graph = grafo;
		DListWords listaInic = grafo.getWords(n, file);
		
		if(listaInic != null){
			//System.out.println(listaInic.toString());
			grafo.show('e');
			System.out.println();
			//2. Creamos el grafo con esas palabras
			for (int i = 0; i< n; i++){
				grafo.addVertexWord(listaInic.getAt(i));
			}
			grafo.addChainings();
			grafo.show('e');
			System.out.println();
			
			//3. Cambiamos las palabras sumidero que podamos
			grafo.sinkRoutine();
			System.out.println();
			grafo.show('e');
			System.out.println();
			grafo.show('f');
		
			System.out.println();
			
			//4. Mostrar listas de recorrido de palabras encadenadas de algunas palabras
			String wtf = "sobrina";
			SQueue cola = grafo.getChainedList(wtf);
						
			//esto era para mostrar todos los posibles caminos desde una palabra
//					SQueue cola2 = new SQueue();
//					if(cola != null){
//						System.out.println("Ahora imprimo las listas resultantes, que van a ser " + cola.getSize());
//						
//						while(!cola.isEmpty()){
//							DListVertex a = (DListVertex) cola.dequeue();
//							System.out.println("Lista " + a.toString());
//							cola2.enqueue(a);
//						}
//					}
			
			if(!cola.isEmpty()){
				System.out.println("La lista más larga que empieza por la palabra '" + wtf +"' es " +grafo.getLongest(cola).toString());
			}else{
				System.out.println("No hay listas posibles que se puedan formar y que partan de la palabra '" + wtf + "'.");
			}
			
			//5. Devolver la lista de palabras encadenadas más larga
			System.out.println();
			DListVertex listaMasLarga = grafo.getLongestChainGame();
			System.out.println("La cadena más larga que se puede formar es: " + listaMasLarga.toString() +", de longitud: "+ listaMasLarga.size);
			
		}else{
			System.out.println("La lista de palabras inicial es null");
		}

		System.out.println("\nPruebas de grafo con palabras encadenadas realizadas.");
	}
	
	
	
	public static void main(String[] args) throws IOException {
	
		prueba1(10);	//prueba para un fichero pequeño con todas las palabras desde el principio, que son encadenadas todas (a propósito)
		//prueba1(5);		//prueba para el mismo fichero pequeño, pero ahora sin coger todas las palabas
		//prueba2(50);	//prueba para un fichero más grande y del cual no sabes a ciencia cierta si va a haber muchas palabras encadenadas (aleatorio)
		
	}
}
