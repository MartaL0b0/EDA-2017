package fase_3.queue;

public interface IQueue {

	public boolean isEmpty();

	public void enqueue(Integer elem);

	public Integer dequeue();

	public Integer front();
	
	public int getSize();
	

}

