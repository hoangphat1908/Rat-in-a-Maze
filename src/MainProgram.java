import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The main program for the Project 4
 * @author Phat Nguyen
 *	
 */
public class MainProgram {
	public static void main(String[] args)
	{
		try(Scanner in = new Scanner(System.in))
		{
		String fileName = args[0];
		TheMaze maze =readFile(fileName);
		mazeDisplay(maze);
		//Randomly generate a new maze
		System.out.println("Maze generator. Input the maze length: n =");
		int mazeLength = in.nextInt();
		TheMaze randomMaze = new TheMaze(mazeLength);
		randomMaze.mazeGeneration();
		mazeDisplay(randomMaze);
		}
		catch(InputMismatchException e)
		{
			System.out.println("Inappropriate Input");
		}
	}
	/**
	 * Generate a maze based on the file format
	 * @param fileName		The file name
	 * @return				The generated maze
	 */
	public static TheMaze readFile(String fileName)
	{
		try(Scanner in = new Scanner(new File(fileName)))
		{
			TheMaze maze = new TheMaze(in.nextInt());
			int i =0;
			while(in.hasNext())
			{
				maze.setRoom(i);
				for(int count = 0;count < 4;count++)
				{
					int open = in.nextInt();
					if(open == 0) maze.getRoom(i).openDoor(count);
				}
				i++;
			}
			return maze;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		return null;
	}
	public static void mazeDisplay(TheMaze m)
	{
		System.out.println("Structure of the maze:");
		m.drawMaze();
		System.out.println("Breadth First Search:");
		m.BFSSolve();
		System.out.println("Depth First Search:");
		m.DFSSolve();
	}
}
