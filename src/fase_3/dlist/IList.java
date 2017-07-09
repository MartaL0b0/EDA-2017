package fase_3.dlist;

/**
 * Lista de objetos de tipo String 
 * @author isegura
 *
 */
public interface IList {

	public void addFirst(int v, String w);

	public void addLast(int v, String w);

	public void removeFirst();

	public void removeLast();
	
	
	public void insertAt(int index, int v, String w);
	
	public boolean isEmpty();

	public boolean contains(int v);

	public int getSize();

	public int getIndexOf(int v);

	public Integer getFirst();

	public Integer getLast();

	public Integer getVertexAt(int index);


	public String toString();

	public void removeAll(int vertex);

	public void removeAt(int index);

}


