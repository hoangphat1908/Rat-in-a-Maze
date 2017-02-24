
public class TheLinkedList {
	class Node {
		private String data;
		private Node next;
		private Node prev;
		/**
		 * Create a single Linked List Node
		 * @param data		data of the Node
		 */
		public Node(String data)
		{
			this.data=data;
		}
	}
	private  Node first;
	private  Node last;
	/**
	 * Create a new Linked List 
	 */
	public TheLinkedList()
		{
			first = null;
			last = null;
		}public void a()
		{
			System.out.println("a1");
		}
	/**
	 * insert a new Node at the beginning of the Linked List
	 * @param data		data of the Node
	 * @return			true/false if the new Node was successfully added
	 */
	public boolean insertFirst(String data)
	{
		boolean result = false;
		try{
		Node newNode = new Node(data);
		if(this.isEmpty())
		{
			last = newNode;
		}
		else
		{
			newNode.next=first;
			first.prev=newNode;
		}
		first = newNode;
		result = true;
		}
		catch (Exception e)
		{
		}
		return result;
		
	}
	/**
	 * insert a new Node at the end of the Linked List
	 * @param data		data of the Node
	 * @return			true/false if the new Node was successfully added
	 */

	public boolean insertLast(String data)
		{
		boolean result = false;
		try{
			Node newNode = new Node(data);
			if(this.isEmpty())
			{
				first = newNode;
			}
			else
			{
				last.next=(newNode);
				newNode.prev=last;
			}
			last = newNode;
			result = true;
		}
		catch(Exception e)
		{
		}
		return result;
		}
	/**
	 * remove the first Node of the Linked List
	 * @return 		data of the removed Node
	 */
	public String removeFirst()
	{
		if(!this.isEmpty())
		{
		if(first.next==null)
			last = null;
		String result = first.data;
		first = first.next;
		if (first!=null)first.prev=null;
		return result;
		}
		else return null;
	}
	/**
	 * remove the last Node of the Linked List
	 * @return		data of the removed Node
	 */
	public String removeLast()
	{
		if(!this.isEmpty())
		{
		if(last.prev==null)
		{
			first = null;
		}
		String result = last.data;
		last = last.prev;
		if(last!=null)last.next=null;
		return result;
		}
		else return null;
	}
	/**
	 * check if the Linked List is empty
	 * @return	true/false
	 */
	public boolean isEmpty()
		{
			return first == null;
		}
	/**
	 * get the data of the first Node 
	 * @return data of the first Node
	 */
	public String getFirst()
	{
		if(!this.isEmpty())
		{
			String result = first.data;
			return result;
		}
		else return null;
	}
/**
 * format the print method of the Linked List
 */
	public String toString ()
	{
		String result="";
		Node current = first;
		while(current != null)
		{
			result+=" "+current.data;
			current = current.next;
		}
		return result;
	}
}
