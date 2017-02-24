
public class TheStack {
	private TheLinkedList stackList;
	private String topData;
	/**
	 * Create a Stack based on Linked List structure.
	 */
public TheStack()
	{
		stackList = new TheLinkedList();
	}
public boolean isEmpty()
	{
		return stackList.isEmpty();
	}
public boolean push(String data)
	{
		return stackList.insertFirst(data);
	}
public boolean pop()
	{
	topData =this.stackList.removeFirst();
	return topData!=null;
	}
public boolean getTop()
	{
	topData =stackList.getFirst();
	return topData!=null;
	}
public String getData()
{
	return topData;
}

public void print ()
	{
	System.out.println("Contents of stack: "+stackList.toString());
	}
}

