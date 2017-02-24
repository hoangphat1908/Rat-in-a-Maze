import java.util.Arrays;

public class Room {
	private int index;
	private int[] doors;
	private boolean visited;
	public Room()
	{
		this.index=-1;
		doors = new int[4];
		Arrays.fill(doors, 1);
		visited=false;
	}
	public void setIndex(int index)
	{
		this.index=index;
	}
	public int getIndex()
	{
		return index;
	}
	public boolean checkDoor(int direction)
	{
		return doors[direction]==0;
	}
	public void refresh()
	{
		visited = false;
	}
	/**
	 * Draw a room shape
	 * @return	The String representation of the room
	 */
	public String roomShape()
	{
		//The sample room drawing
		//       N
		//		*--*							*--*	
		//	   W|  |E		Visited room:       |XX|	
		//		*--*							*--*
		//        S
		String result = "*--*\n|  |\n*--*";
		result ="*";
		if(doors[0]==1) result+="--*\n";
		else			result+="  *\n";
		if(this.visited)
		{
			if(doors[3]==1) result+="|XX";
			else			result+=" XX";
		}
		else
		{
			if(doors[3]==1) result+="|  ";
			else			result+="   ";
		}
		if(doors[2]==1) result+="|\n*";
		else			result+=" \n*";
		
		if(doors[1]==1) result+="--*";
		else			result+="  *";
		return result;
	}
	/**
	 * Visit the room
	 */
	public void visit()
	{
		visited=true;
	}
	/**
	 * Check if the room is visited
	 * @return	True/False
	 */
	public boolean isVisited()
	{
		return visited;
	}
	/**
	 * Open the door in the given direction
	 * @param direction		The given direction
	 */
	public void openDoor(int direction)
	{
		this.doors[direction]=0;
	}
}
