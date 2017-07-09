package fase_3.queue;


public class SQueueGraph implements IQueue {
	
	private SNodeGraph first;
	private SNodeGraph last;
	int size;

	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(Integer vertex) {
		SNodeGraph node = new SNodeGraph(vertex);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		size++;
	}

	public Integer dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		} 
		
		Integer firstvertex = first.vertex;
		first = first.next;
		if (first == null) {
			last = null;
		}
		size--;
		return firstvertex;
		
	}

	public Integer front() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return null;
		}
		return first.vertex;
	}

	@Override
	public String toString() {
		String result = null;
		for (SNodeGraph nodeIt = first; nodeIt != null; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "[" + nodeIt.vertex + "]";
			} else {
				result += "," + nodeIt.vertex;
			}
		}
		return result == null ? "empty" : result;
	}

	


	@Override
	public int getSize() {
		return size;
	}
}
