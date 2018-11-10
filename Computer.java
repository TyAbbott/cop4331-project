
import java.util.ArrayList;
import java.util.Arrays;

public class Computer{
	static final int PLAYER_ONE_SQUARE = 1;
	static final int PLAYER_TWO_SQUARE = 2;
	static final int EMPTY_SQUARE = 0;
	public int[] getNextMove(Game_Logic game_Logic, int whichIsPlaying, int boardWidth) {
		ArrayList<int[]>[] iMove = rankChoices(game_Logic, whichIsPlaying, boardWidth);
		ArrayList<int[]>[] theyMoves = rankChoices(game_Logic, PLAYER_ONE_SQUARE ^ PLAYER_TWO_SQUARE ^ whichIsPlaying, boardWidth);
		
		// stop the other one from moving first
		if(!theyMoves[0].isEmpty()){
			int dex = (int)(Math.random()*theyMoves[0].size());
			return theyMoves[0].get(dex);
		}
		
		for(int i = 0; i <= game_Logic.getGoal(); i++){
			boolean theyGotMoves = !theyMoves[i].isEmpty();
			boolean iGotMoves = !iMove[i].isEmpty();
			if(theyGotMoves && iGotMoves){
				// there's more chance we build ourselves if there are more ways we can increase
				// there's more chance we block oppponent otherwise
				int dex = (int)(Math.random()* (theyMoves[i].size() + iMove[i].size()));
				
				if(dex >= theyMoves[i].size()){
					dex -= theyMoves[i].size();
					return iMove[i].get(dex);
				}
				else{
					return theyMoves[i].get(dex);
				}
			}
			else if(theyGotMoves){
				// block them
				int dex = (int)(Math.random()*theyMoves[i].size());
				return theyMoves[i].get(dex);
			}
			else if(iGotMoves){
				// build ourself
				int dex = (int)(Math.random()*iMove[i].size());
				return iMove[i].get(dex);
			}
		}
		
		// cannot choose anything.
		return null;
	}
	
	// this function ranks possible moves from
	// // best (0 away from winning)  to
	// // worst (gameLogic.getGoal() away from winning)
	// and returns that ranking list.
	static ArrayList<int[]>[] rankChoices(Game_Logic game_Logic, int player, int width){
		// a piece in choices[i] is i plays away from winning.
		// every int[] stored here is actually a [row, column] coordinate.
		ArrayList<int[]>[] choices = new ArrayList[game_Logic.getGoal() + 1];
		for(int i = 0; i <= game_Logic.getGoal(); i++){
			choices[i] = new ArrayList<>();
		}
		int bestDistance = Integer.MAX_VALUE;
		
		int numEntries = 0;
		// get how good each piece is
		for(int r = 0; r < width; r++){
			for(int c = 0; c < width; c++){
				if(game_Logic.getMapValueAt(r, c) == EMPTY_SQUARE){
					int winDist = winningDistance(game_Logic, player, width, r, c);
					choices[winDist].add(new int[]{r,c});
					numEntries++;
				}
			}
		}
		if(numEntries == 0){
			return null;
		}
		
		return choices;
	}
	
	// returns K minus how many in a row one can make
	// by placing a single piece. it does NOT return the
	// number of remaining moves to get K in a row.
	static int winningDistance(Game_Logic game_Logic, int player, int width, int row, int col){
		int[][] board = game_Logic.getMap();
		
//		for(int[] a : board) System.out.println(Arrays.toString(a));
//		System.out.println(board[row][col]);
//		
		if(board[row][col] != EMPTY_SQUARE){
			return game_Logic.getGoal();
		}
		int[] directions = new int[8];
		for(int i = 0, dr = -1; dr <= 1; dr++){
			for(int dc = -1; dc <= 1; dc++, i++){
				if(dr == 0 && dc == 0){
					i--;
					continue;
				}
//				System.out.println(i);
				
				for(int k = 1; k < game_Logic.getGoal(); k++){
					int propRow = row + k*dr;
					int propCol = col + k*dc;
					
					// is it out of bounds?
					if(0 > propRow || propRow >= width || 0 > propCol || propCol >= width){
						break;
					}
					// is it something other than our piece?
					if(board[propRow][propCol] != player){
						break;
					}
					
					// so it's our piece.
					// cool.
					directions[i]++;
				}
			}
		}
		
		
		// the maximum direction in tic tac toe
		int maximum = 1;
		for(int i = 0; i < 4; i++){
			maximum = Math.max(maximum, 1 + directions[i] + directions[7-i]);
		}
		
		// 
		return Math.max(0, game_Logic.getGoal() - maximum);
	}
	
}
