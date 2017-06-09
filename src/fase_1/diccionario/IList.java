package fase_1.diccionario;

import cola.SQueue;

/**
 * Lista de objetos de tipo String 
 * @author isegura
 *
 */
public interface IList {

	public void add(SQueue cola);

	public void add(String word);

	public void show(char c);

	public int search(String word);
	
	public DList getTop(int n);
	
	public DList getLow(int n);
	
	
	

}


