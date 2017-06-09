package cola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SQueue implements IQueue {

	// cola terminada y funcional

	private SNode first;
	private SNode last;
	int size;

	public boolean isEmpty() {
		return first == null;
	}

	public void enqueue(Object elem) {
		SNode node = new SNode(elem);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		size++;
	}

	public Object dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		}

		Object firstElem = first.elem;
		first = first.next;
		if (first == null) {
			last = null;
		}
		size--;
		return firstElem;

	}

	
	
	
	//este no se usa
	public Object front() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		}
		return first.elem;
	}

	@Override
	
	public String toString() {
		String result = null;
		for (SNode nodeIt = first; nodeIt != null; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "" + nodeIt.elem.toString() + "";
			} else {
				result += "\n" + nodeIt.elem.toString();
			}
		}
		return result == null ? "empty" : result;
	}

	@Override
	//este no se usa
	public int getSize() {
		return size;
	}

	public void readFile(String archivo) throws FileNotFoundException, IOException {
		String cadena;
		String wordaux = "";
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);

		while ((cadena = b.readLine()) != null) {

			cadena = cadena.toLowerCase();

			for (int i = 0; i < cadena.length(); i++) {

				//aqui en la memoria especificar y justificar por qué se ordena con las tildes al final
				if (((int) cadena.charAt(i) >= 61 && (int) cadena.charAt(i) <= 122) || (int) cadena.charAt(i) == 225
						|| (int) cadena.charAt(i) == 233 || (int) cadena.charAt(i) == 237
						|| (int) cadena.charAt(i) == 243 || (int) cadena.charAt(i) == 250
						|| (int) cadena.charAt(i) == 252 || (int) cadena.charAt(i) == 241) {

					
						wordaux += cadena.charAt(i);
					
				
				} else {
					if (wordaux != "") {
						enqueue(wordaux);
					}
					wordaux = "";
				}

			}

			if (wordaux != "") {
				enqueue(wordaux);
			}
			wordaux = "";
		}
		b.close();
	}
	
	public void readFileNoAccent(String archivo) throws FileNotFoundException, IOException {
		String cadena;
		String wordaux = "";
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);

		while ((cadena = b.readLine()) != null) {

			cadena = cadena.toLowerCase();

			for (int i = 0; i < cadena.length(); i++) {

				//aqui en la memoria especificar y justificar por qué se ordena con las tildes al final
				if (((int) cadena.charAt(i) >= 61 && (int) cadena.charAt(i) <= 122) || (int) cadena.charAt(i) == 225
						|| (int) cadena.charAt(i) == 233 || (int) cadena.charAt(i) == 237
						|| (int) cadena.charAt(i) == 243 || (int) cadena.charAt(i) == 250
						|| (int) cadena.charAt(i) == 252 || (int) cadena.charAt(i) == 241) {

					
					if ((int)cadena.charAt(i) == 225){
						wordaux += 'a';
					}else if((int)cadena.charAt(i) == 233){
						wordaux += 'e';
					}else if((int)cadena.charAt(i) == 237){
						wordaux += 'i';
					}else if((int)cadena.charAt(i) == 243){
						wordaux += 'o';
					}else if((int)cadena.charAt(i) == 250 || (int)cadena.charAt(i) == 252){
						wordaux += 'u';
					}else{
						wordaux += cadena.charAt(i);
					}
				
				} else {
					if (wordaux != "") {
						enqueue(wordaux);
					}
					wordaux = "";
				}

			}

			if (wordaux != "") {
				enqueue(wordaux);
			}
			wordaux = "";
		}
		b.close();
	}
	
	
}
