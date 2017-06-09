package cola;

public interface IQueue {

	public boolean isEmpty();

	public void enqueue(Object elem);

	public Object dequeue();

	public Object front();
	
	public int getSize();
	

}

