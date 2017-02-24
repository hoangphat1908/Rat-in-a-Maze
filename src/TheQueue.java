
public class TheQueue extends TheLinkedList
{
	private TheLinkedList queueList;
	private String topData;
	
		public void a()
		{
			System.out.println("a2");
		}
	
	/**
	 * Create a Queue based on Linked List structure.
	 */
public TheQueue()
{
	queueList = new TheLinkedList();
}
public boolean isEmpty()
{
	return queueList.isEmpty();
}
public boolean enqueue(String data)
{
	return queueList.insertLast(data);
}
public boolean dequeue()
{
	topData =this.queueList.removeFirst();
	return topData!=null;
	
}
public String getData()
{
	return topData;
}

public void print ()
{
	System.out.println("Contents of queue: "+queueList.toString());
}

}
