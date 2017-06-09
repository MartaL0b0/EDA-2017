package fase_1;

import java.io.IOException;

import cola.SQueue;
import fase_1.diccionario.DictionaryList;




public class Main_Fase1 {

	public static void main(String[] args) throws IOException {
		
		SQueue colaux = new SQueue();
		String file = "quijoteCorto.txt";
		System.out.println("Leyendo archivo y creando diccionario...");
		try {     // La siguiente linea puede lanzar una excepcion de tipo IOException
			colaux.readFile(file);                       
        } catch (IOException ioex) { 
            // Capturamos la excepcion IOException
        	System.out.println("File " + file + " not found in "+System.getProperty("user.dir"));
            throw ioex;
        } 	
		
		//System.out.println(colaux.toString());
		//System.out.println(colaux.getSize()); Para comprobar que me lista las palabras bien, las cuento
	
		DictionaryList dic = new DictionaryList();
		dic.add(colaux);
		System.out.println();
		
		System.out.println("Mostrando diccionario en orden alfabético ascendente...");
		dic.show('A');
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("Mostrando diccionario en orden alfabético descendente...");
		dic.show('b');
		System.out.println();
		System.out.println();
		
		
		System.out.println("Análisis de frecuencias...");
		System.out.println("La frec de *de* es: " + dic.search("de"));
		System.out.println("La frec de *quijote* es: " + dic.search("quijote"));
		System.out.println("La frec de *algún* es: " + dic.search("algún"));
		System.out.println();
		System.out.println();
		
		System.out.println("Lista de las 5 palabras con mayor frecuencia: \n" + dic.getTop(5).toString() + "\n");
		System.out.println("Lista de las 5 palabras con menor frecuencia: \n" + dic.getLow(5).toString() + "\n");
		System.out.println("Lista de las 20 palabras con mayor frecuencia: \n" + dic.getTop(20).toString() + "\n");
		System.out.println("Lista de las 20 palabras con menor frecuencia: \n" + dic.getLow(20).toString() + "\n");	
		
		System.out.println("\nPruebas de lista diccionario realizadas.");

	}

}
