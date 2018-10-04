
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileReader;
import java.util.Scanner;

// TO USE THE CLASS TO LOAD/SAVE:
// -------------------------------
// Load_Save_Game game = new Load_Save_Game(int[][] board, int playerturn, int goal);
// boolean check = game.save("filename");
// boolean check = game.load("filename");
// board = game.getBoard();
// player = game.getPlayer();
// goal = game.getGoal();
// size = game.getSize();

public class Load_Save_Game 
{
	// variables
	private static int size;
	private static int goal;
	private static int player;
	private static int[][] board;
	
	public static void main(String[] args)
	{
		
		int [][] x = new int[][]{
			{0, 1, 0},
			{1, 2, 2},
			{0, 0, 0}
		};
		setBoard(x);
		setGoal(3);
		setPlayer(2);
		setSize(3);
		boolean check = save("t1.txt");
		check = load("t2.txt");
		check = save("t3.txt");
		System.out.println(check);
	}
	
	// constructor takes the game board, player, and goal
	public Load_Save_Game(int [][] board, int player, int goal, int size)
	{
		this.board = board;
		this.player = player;
		this.goal = goal;
		this.size = size;
	}

	public Load_Save_Game()
	{
		// does nothing
	}
	
	/*
	** File Format:
	** int size
	** int goal
	** int playerturn
	** int[][] board
	*/
	public static boolean load(String filename)
	{
		// Variables
		int size, goal, player;
		int[][] board;
		Scanner scan;

		FileReader fr = null;
		
		try
		{
			File file = new File(filename);
			scan = new Scanner(file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
		// Read through input, setting variables accordingly
		size = Integer.parseInt(scan.nextLine());
		goal = Integer.parseInt(scan.nextLine());
		player = Integer.parseInt(scan.nextLine());
		board = new int[15][15];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++)
			{
				board[i][j]=Integer.parseInt(scan.nextLine());
			}
		}
		
		// Setting variables before returning
		setSize(size);
		setGoal(goal);
		setPlayer(player);//fixed
		setBoard(board);
		return true;	
	}
	
	/*
	** Saves each value as an int seperated by spaces
	** Save file format:
	** int size
	** int goal
	** int playerturn
	** int[][] board
	*/
	public static boolean save(String filename)
	{
		// Variables
		int[][] map = board;
		int playerturn = player;
		int target = goal;
		int size1 = size; 
		
		PrintStream ps = null;
		
		try
		{
                                                                // Directory of where saves are stored
			File file = new File(System.getProperty("user.home") + "/Desktop/" + filename);
			ps = new PrintStream(file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
		// Writes size, goal, and playerturn to file
		ps.println(size1);
		ps.println(target);
		ps.println(playerturn);
		
		// Loop through the game board
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				ps.println(map[i][j]);
			}
		}
	
		// Closes the PrintStream
		ps.close();	
		return true;
	}
	
	// Getters and Setters
	private static void setSize(int x)
	{
		size = x;
	}
	
	public int getSize()
	{
		return size;
	}
	
	private static void setPlayer(int x)
	{
		player = x;
	}
	
	public int getPlayer()
	{
		return player;
	}
	
	private static void setGoal(int x)
	{
		goal = x;
	}
	
	public int getGoal()
	{
		return goal;
	}
	
	private static void setBoard(int[][] xs)
	{
		board = xs;
	}
	
	public int[][] getBoard()
	{
		return board;
	}
		
	
}