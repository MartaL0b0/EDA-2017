package fase_2.dictionarytree;

import cola.SQueue;
import fase_1.diccionario.DList;




public interface IDictionaryTree {

	public void add(SQueue cola);
	
	public void add(String word);
	
	public void show(char a);
	
	public int search(String word);
	
	public DList getTop(int n);
	
	public DList getLow(int n);
}
