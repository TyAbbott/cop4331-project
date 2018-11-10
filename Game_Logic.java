
import java.util.Arrays;
import java.util.Scanner;

public class Game_Logic {
// map[y][x] == piece
	
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			test_checkForWin();
			if(QUIT) break;
		}
	}
	
	static boolean QUIT = false;
	public static boolean test_checkForWin(){
		// input the board size
		Game_Logic tttc = new Game_Logic();
		int N = in.nextInt();
		if(N == 0){
			QUIT = true;
			return false;
		}
		
		// input the board
		tttc.map = new int[N][N];
		for(int r = 0; r < N; r++){
			// 0 for space, 1 and 2 for player choices
			String row = in.next();
			for(int c = 0; c < N; c++){
				tttc.map[r][c] = row.charAt(c) - '0';
				
			}
			
		}
		// input the (row, col) and win threshold
		int R = in.nextInt(), C = in.nextInt(), W = in.nextInt();
		tttc.goal = W;
		
		boolean result = tttc.checkForWin(R, C);
		System.out.println(result);
		return result;
	}
	

	// CODE THAT DOES NOT GO IN THE FINAL THING IS ABOVE ////////////////////////////////
	// CODE THAT DOES GOES IN THE FINAL THING IS BELOW //////////////////////////////////
	
	private int goal;
	private int[][] map;
	private int size;
	
	public int getGoal(){ return goal; }
	public int setGoal(int newGoal){ return goal = newGoal; }
	
	public int[][] getMap(){ return map; }
        
        public void loadGame(int[][] newMap, int newGoal) 
        { 
            map = newMap; 
            goal = newGoal;
        }
	
	// returns 0 if no piece was placed,
	// 1,2, ... if player 1,2, ... placed a piece here,
	// throws ArrayIndexOutOfBoundsException if it's out of bounds
	public int getMapValueAt(int row, int col){
		return map[row][col];
	}
	
	private Game_Logic(){}
	public Game_Logic(int size, int goal){
		map = new int[30][30];
                
		this.goal = goal;
		this.size = size;
	}
	public boolean updateBoard(int row, int col, int player){
		if(map[row][col] != 0){
			return false;
		}
		map[row][col] = player;
		return true;
	}
	public boolean checkForWin(int row, int col){
		// sums[] represents the number of consecutive characters equal to map[y][x] in a direction.
		// directions are encoded as integers.
		int[] sums = new int[8];
		int dex = 0;
		for(int dr = -1; dr <= 1; dr++){
			for(int dc = -1; dc <= 1; dc++){
				if(dc == 0 && dr == 0) continue;
				// do stuff
				int count = 0;
				for(int k = 1;; k++){
					int checkC = col + dc*k, checkR = row + dr*k;
//					System.out.println(checkR+" "+checkC+" is checkR and checkC respectively");
					// if it's NOT In bounds, it must be out of bounds
					if(!(0 <= checkC && checkC < size && 0 <= checkR && checkR < size)){
//						System.out.println("nope");
						break;
					}
					if(map[checkR][checkC] == map[row][col]){
						count++;
					}
					else{
						break;
					}
				}
				
				sums[dex] += count;
				dex++;
			}
		}
		
//		System.out.println(Arrays.toString(sums));
		
		// because of the way we encoded directions as integers,
		// the first thing is opposite the last thing,
		// the second thing is opposite the second to last thing, etc
		int max = 1;
		for(int i = 0; i < 4; i++){
			max = Math.max(max, sums[i] + sums[sums.length - i - 1] + 1);
		}
		
		if(max >= goal) return true;
		return false;
	}

	// clearBoard populates the board with all 0's
	public void clearBoard() {
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				map[i][j] = 0;
			}
		}
	}
}
