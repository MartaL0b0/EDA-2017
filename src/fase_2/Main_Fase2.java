package fase_2;

import java.io.IOException;

import cola.SQueue;
import fase_1.diccionario.DictionaryList;
import fase_2.dictionarytree.DictionaryTree;
import fase_2.dictionarytreefreq.DictionaryTreeFreq;



public class Main_Fase2 {

	public static void prueba1() throws IOException{
		SQueue colaux = new SQueue();
		String file = "quijoteCorto.txt";
	
		System.out.println("Prueba para �rbol diccionario. Leyendo archivo " + file + " y creando diccionario...");
		try {     // La siguiente linea puede lanzar una excepcion de tipo IOException
			colaux.readFile(file);                       
        } catch (IOException ioex) { 
            // Capturamos la excepcion IOException
        	System.out.println("File " + file + " not found in "+System.getProperty("user.dir"));
            throw ioex;
        }
		
//		System.out.println(colaux.toString());
		System.out.println("N�mero de palabras del diccionario:" + colaux.getSize()); //Para comprobar que me lista las palabras bien, las cuento
		
		DictionaryTree tree = new DictionaryTree();
		tree.add(colaux);
		System.out.println();
		
		System.out.println("Mostrando el diccionario en orden alfab�tico ascendente...");
		tree.show('A');
		System.out.println();
		
		System.out.println("Mostrando el diccionario en orden alfab�tico descendente...");
		tree.show('a');	
		System.out.println();
		
		System.out.println("An�lisis de frecuencias...");
		System.out.println("La frec de *de* es: " + tree.search("de"));
		System.out.println("La frec de *los* es: " + tree.search("los"));
		System.out.println();
		
		System.out.println("Lista de las 5 palabras con mayor frecuencia: \n" + tree.getTop(5).toString()+ "\n");
		System.out.println("Lista de las 5 palabras con menor frecuencia: \n" +tree.getLow(5).toString()+ "\n");
		System.out.println("Lista de las 20 palabras con mayor frecuencia: \n"+ tree.getTop(20).toString()+ "\n");
		
		
		System.out.println("Pruebas de �rboles diccionario realizadas.");
	}
	
	public static void prueba2() throws IOException{
		SQueue colaux = new SQueue();
		DictionaryList list = new DictionaryList();
		
		String file2 = "quijoteCorto.txt";
		System.out.println("Prueba para �rbol por frecuencias. Leyendo archivo " + file2 + " y creando �rbol...");
		try {     // La siguiente linea puede lanzar una excepcion de tipo IOException
			colaux.readFile(file2);                       
        } catch (IOException ioex) { 
            // Capturamos la excepcion IOException
        	System.out.println("File " + file2 + " not found in "+System.getProperty("user.dir"));
            throw ioex;
        }
		
	
		System.out.println();
		list.add(colaux);
		System.out.println("Mostrando la lista diccionario con la que se formar� el �rbol por frecuencias...");
		list.show('A');
		
		DictionaryTreeFreq treeFreq = new DictionaryTreeFreq();
		treeFreq.save(list);
		System.out.println();
		System.out.println("�rbol creado con la lista. Mostrando...");
		treeFreq.root.getInorder();
		System.out.println("\n");
		
		
		String w1 = "de";
		String w2 = "superstar";
		int f1 = 2;
		int f2 = 1;
		System.out.println("A�adiendo una nueva palabra al �rbol: '" + w1 + "', con frecuencia " + f1);
		treeFreq.add(w1, f1);
		System.out.println("\nA�adiendo una nueva palabra al �rbol: '" + w2 + "', con frecuencia " + f2);
		treeFreq.add(w2, f2);
		System.out.println();
		
		System.out.println("�rbol modificado. Mostrando...");
		treeFreq.root.getInorder();
		
		System.out.println("\nPruebas de �rboles por frecuencias realizadas.");
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		
		prueba1();
		System.out.println("\n\n");
		prueba2();
		
		
	}

}
