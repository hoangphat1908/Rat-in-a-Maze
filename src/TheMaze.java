import java.util.Random;

public class TheMaze {
	private int mazeLength;
	private Room[] theRooms;
	/**
	 * Create a maze with a given maze length n
	 * @param mazeLength	length of the maze (n)
	 */
	public TheMaze(int mazeLength)
	{
		this.mazeLength=mazeLength;
		theRooms = new Room[mazeLength*mazeLength];
	}
	/**
	 * Create a new room
	 * @param number	room number
	 */
	public void setRoom(int number)
	{
		theRooms[number] = new Room();
	}
	/**
	 * return the room with the given room number		
	 * @param number	room number
	 * @return			the specified room
	 */
	public Room getRoom(int number)
	{
		return theRooms[number];
	}
	public void mazeReconstruct()
	{
		//Create a new maze
				theRooms = new Room[mazeLength*mazeLength];
				for(int count=0;count<mazeLength*mazeLength;count++)
				{
					theRooms[count] = new Room();
				}
		//Open the North door of the entrance and the South door of the exit
		theRooms[0].openDoor(0);
		theRooms[mazeLength*mazeLength-1].openDoor(1);
	}
	/**
	 * Randomly generate a new maze
	 */
	public void mazeGeneration()
	{
		this.mazeReconstruct();
		//While the entrance and the exit is not on the same set
		while (this.find(0)!=this.find(mazeLength*mazeLength-1))
		{
			//The method randomly generate an array of two adjacent room numbers
			int[] randomRooms = this.findRandomPair();
			int i = randomRooms[0];
			int j = randomRooms[1];
			//If the two rooms are not on the same set, union the sets and open the door between the rooms
			if(this.find(i)!=this.find(j))
			{
				this.connectRooms(i, j);
				this.union(this.find(i),this.find(j));
			}
		}
	}
	/**
	 * Find the root of the set that contains the room number
	 * @param number	The room number
	 * @return			The root of the set
	 */
	public int find(int number)
	{
		int index = theRooms[number].getIndex();
		if(index<0) return number;
		else return this.find(index);
		
	}
	/**
	 * Union the sets of two roots
	 * @param root1		The root of set 1
	 * @param root2		The root of set 2
	 */
	public void union(int root1, int root2)
	{
		int firstIndex = theRooms[root1].getIndex();
		int secondIndex = theRooms[root2].getIndex();
		if(secondIndex<firstIndex)
		{
			theRooms[root2].setIndex(firstIndex+secondIndex);
			theRooms[root1].setIndex(root2);
		}
		else
		{
			theRooms[root1].setIndex(firstIndex+secondIndex);
			theRooms[root2].setIndex(root1);
		}
	}
	/**
	 * Open the doors between two adjacent rooms
	 * @param first		The first room
	 * @param second	The second room	
	 */
	public void connectRooms(int first, int second)
	{
		int subtraction = first - second;
		//The second room is north of the first room 
		if(subtraction==mazeLength)
		{
			theRooms[first].openDoor(0);
			theRooms[second].openDoor(1);
		}
		//The second room is south of the first room 
		if(subtraction==-mazeLength)
		{
			theRooms[first].openDoor(1);
			theRooms[second].openDoor(0);	
		}
		//The second room is east of the first room 
		if(subtraction==-1)
		{
			theRooms[first].openDoor(2);
			theRooms[second].openDoor(3);
		}
		//The second room is west of the first room 
		if(subtraction==1)
		{
			theRooms[first].openDoor(3);
			theRooms[second].openDoor(2);	
		}
	}
	/**
	 * Randomly generate two adjacent room numbers
	 * @return	Array contains the room numbers
	 */
	public int[] findRandomPair()
	{
		Random random = new Random();
		int[] result = new int[2];
		//Generate the first room number between 0 and n^2-1
		int firstRoom = random.nextInt(mazeLength*mazeLength);
		result[0]=firstRoom;
		//The method returns an array of maximum four rooms adjacent to the first, in N, S, E and W
		//The walls are marked "-1"
		int[] adjacentRooms =  this.findAdjacentRooms(firstRoom);
		int adjacent;
		//Randomly choose a room in the adjacent rooms
		do
		{
			adjacent = random.nextInt(4);
		}
		while(adjacentRooms[adjacent]<0);
		
		result[1]=adjacentRooms[adjacent];
		return result;
	}
	/**
	 * Find the rooms adjacent to the given room number
	 * @param center	the center room number
	 * @return			the adjacent rooms
	 */
	public int[] findAdjacentRooms(int center)
	{
		int[] result ={-1,-1,-1,-1};
		//If the room is bounded north
		if(!this.isNorthBound(center)) result[0]=center-mazeLength;
		//If the room is bounded south
		if(!this.isSouthBound(center)) result[1]=center+mazeLength;
		//If the room is bounded east
		if(!this.isEastBound(center)) result[2]=center+1;
		//If the room is bounded west
		if(!this.isWestBound(center)) result[3]=center-1;
		return result;
	}
	/**
	 * Check if the room is bounded north
	 * @param number	The room number
	 * @return	true/false
	 */
	public boolean isNorthBound(int number)
	{
		return number<mazeLength;
	}
	/**
	 * Check if the room is bounded south
	 * @param number	The room Number
	 * @return	true/false
	 */
	public boolean isSouthBound(int number)
	{
		return number>mazeLength*(mazeLength-1)-1;
	}
	/**
	 * Check if the room is bounded east
	 * @param number	the room number
	 * @return	true/false
	 */
	public boolean isEastBound(int number)
	{
		for(int count = 1;count<=mazeLength;count++)
		{
			if(number==count*mazeLength-1) return true;
		}
		return false;
	}
	/**
	 * Check if the room is bounded west
	 * @param number	the room number
	 * @return	true/false
	 */
	public boolean isWestBound(int number)
	{
		for(int count = 0;count<mazeLength;count++)
		{
			if(number==count*mazeLength) return true;
		}
		return false;
	}
	/**
	 * Uncheck the visited rooms
	 */
	public void refreshVisit()
	{
		for(Room room: theRooms)
		{
			room.refresh();
		}
	}
	/**
	 * Solve the maze using the Breadth First Search
	 */
	public void BFSSolve()
	{
		TheQueue Q = new TheQueue();
		Q.enqueue(0+"");
		theRooms[0].visit();
		String result="0";
		String path = "";
		while(!Q.isEmpty())
		{
			Q.dequeue();
			int i = Integer.parseInt(Q.getData());
			
			int[] adjacentRooms =this.findAdjacentRooms(i);
			for(int count=0;count<4;count++)
			{
				int roomNumber = adjacentRooms[count];
				if(roomNumber>=0&&this.adjacentPassage(i, roomNumber, count)&&!theRooms[roomNumber].isVisited())
				{
					result+=" "+roomNumber;
					Q.enqueue(roomNumber+"");
					theRooms[roomNumber].visit();
					//If the search find the exit, print the path found
					if(roomNumber==mazeLength*mazeLength-1)
					{
						System.out.println("Rooms visited by BFS: "+result);
						path = this.findPath(result);
						System.out.println("This is the path (in reverse): "+this.findPath(result));
						break;
					}
				}
			}
			
		}
		//Refresh the visited rooms and draw the maze path
		this.refreshVisit();
		this.drawSolvedMaze(path);
		
	}
	/**
	 * Solve the maze using the Depth First Search
	 */
	public void DFSSolve()
	{
		TheStack Q = new TheStack();
		Q.push(0+"");
		theRooms[0].visit();
		String result="";
		String path="";
		while(!Q.isEmpty())
		{
			Q.pop();
			int i = Integer.parseInt(Q.getData());
			result+=" "+i;
			int[] adjacentRooms =this.findAdjacentRooms(i);
			//If the search find the exit, print the path found
			if(i==mazeLength*mazeLength-1)
			{
				result = result.substring(1);
				System.out.println("Rooms visited by DFS: "+result);
				path = this.findPath(result);
				System.out.println("This is the path (in reverse): "+path);
				break;
			}
			for(int count=0;count<4;count++)
			{
				int roomNumber = adjacentRooms[count];
				if(roomNumber>=0&&this.adjacentPassage(i, roomNumber, count)&&!theRooms[roomNumber].isVisited())
				{
					
					Q.push(roomNumber+"");
					theRooms[roomNumber].visit();
					
				}
			}
			
		}
		//Refresh the visited rooms and draw the maze path
		this.refreshVisit();
		this.drawSolvedMaze(path);
		
	}
	/**
	 * Check if the second room is adjacent and is opened to the first room in the given direction
	 * @param first		The first room number
	 * @param second	The	second room number
	 * @param direction	The direction of the second room to the first room
	 * @return			True/False
	 */
	public boolean adjacentPassage(int first, int second,int direction)
	{
		//Check if the rooms are adjacent
		int[] adjacentRooms = this.findAdjacentRooms(first);
		//Check if the doors connecting the two rooms are connected
		if(second == adjacentRooms[direction])
		{
			if(theRooms[first].checkDoor(direction)&&theRooms[second].checkDoor(Math.floorMod(5-direction, 4)))
			{
				return true;
			}
		}
		return false;              
	}
	/**
	 * Find a path (In reversed order) to solve the maze based on the path generate by BFS and DFS
	 * @param search	The generated path by BFS and DFS
	 * @return			The reversed maze path
	 */
	public String findPath(String search)
	{
		String[] rooms = search.split("\\s+");
		String result = rooms[rooms.length-1];
		int before = Integer.parseInt(result);
		for(int count = rooms.length-2;count >=0;count--)
		{
			//Start from the exit room
			int current = Integer.parseInt(rooms[count]);
			int direction = 0;
			//Check if the current room is connected to the "before" room
			while(direction<4)
			{
				if(this.adjacentPassage(before, current, direction))
				{
					result+=" "+current;
					before = current;
					break;
				}
				direction++;
			}
		}
		return result;
	}
	/**
	 * Draw the maze with the path
	 * @param path	the given path
	 */
	public void drawSolvedMaze(String path)
	{
		String[] rooms = path.split(" ");
		//Visit the rooms whose number appear in the given path
		for(String room: rooms)
		{
			theRooms[Integer.parseInt(room)].visit();
		}
		this.drawMaze();
		this.refreshVisit();
	}
	/**
	 * Draw the maze
	 */
	public void drawMaze()
	{
		
		for(int i = 0;i<mazeLength;i++)
		{
			String result="";
			for(int j=0;j<mazeLength;j++)
			{
				//Draw the rooms from left to right, from top to bottom
				int number = i*mazeLength+j;
				//Concatenate the room drawings
				result = connectString(result, theRooms[number].roomShape());
			}
			System.out.print(result);
		}
		
	}
	/**
	 * Concatenate the new room drawing s2 to the base drawing s1
	 * @param s1	The base drawing
	 * @param s2	The new room drawing
	 * @return		The resulting string
	 */
	public String connectString(String s1, String s2)
	{
		String result="";
		if(s1.length()==0) return s2;
		int n = (s1.length()-2)/12;
		result+=s1.substring(0,4*n)+s2.substring(0, 4)+"\n";
		result+=s1.substring(4*n+1, 8*n+1)+s2.substring(5, 9)+"\n";
		result+=s1.substring(8*n+2,12*n+2)+s2.substring(10, 14)+"\n";
		return result;
	}

}
