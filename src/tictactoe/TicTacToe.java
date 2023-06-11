package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private static final char EMPTY_POSITION = '-';
	private char[] board = new char[] {     EMPTY_POSITION, EMPTY_POSITION, EMPTY_POSITION,
	                                     	EMPTY_POSITION, EMPTY_POSITION, EMPTY_POSITION,
	                                     	EMPTY_POSITION, EMPTY_POSITION, EMPTY_POSITION};
	private final char  P1 = 'X';
	private final char  P2 = 'O';
	private  char currentPlayer = P1;
	private  char winner = ' ';
	private  boolean isGameRuuning = true;
	private Scanner sc;
	
	private  List<Integer> freePositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	
	public void play() {
		sc = new Scanner(System.in);
		System.out.println("Welcome For Tow players Mood Enter 1, For One Player Mood Enter 2:");
		try{
			var mode = sc.nextInt();
			if(mode == 1)
				twoPlayersTicTacToe();
			if(mode == 2)
				playerVsPc();
			else 
				throw new Exception();
		}catch(Exception e) {
			System.out.println("Please Inter A Valid Response..");
			play();
		}
		//sc.close();
	}
	//printing the game board
	private void prindBoard() {
		System.out.println();
		System.out.println();
		System.out.println("              " +   " "    + " %%  " +   " "    + "  %% ");
		System.out.println("              " + board[0] + " %%  " + board[1] + "  %% " + board[2]);
		System.out.println("              " +   " "    + " %%  " +   " "    + "  %% ");
		System.out.println(                 "           " + "%%%%%%%%%%%%%%%%%%%");
		System.out.println("              " +   " "    + " %%  " +   " "    + "  %% ");
		System.out.println("              " + board[3] + " %%  " + board[4] + "  %% " + board[5]);
		System.out.println("              " +   " "    + " %%  " +   " "    + "  %% ");
		System.out.println(                 "           " + "%%%%%%%%%%%%%%%%%%%");
		System.out.println("              " +   " "    + " %%  " +   " "    + "  %% ");
		System.out.println("              " + board[6] + " %%  " + board[7] + "  %% " + board[8]);
		System.out.println("              " +   " "    + " %%  " +   " "    + "  %% ");
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	//take player input
	private void playerInputToTheBord(int input) throws Exception{
		if(freePositions.contains(input) ){
			freePositions.removeIf((n) -> n == input);
			board[input - 1] = currentPlayer;
		}else
			throw new Exception();
	}
	private void pcInputToTheBord(int input){
			freePositions.removeIf((n) -> n == input);
			board[input - 1] = currentPlayer;
	}
	
	//check for win or tie
	private boolean checkHorizontal() {
		if(
			isPositionMarksMatch(0, 1, 2) ||
			isPositionMarksMatch(3, 4, 5) ||
			isPositionMarksMatch(6, 7, 8) ){
			winner = currentPlayer;
			return true;
		}
		return false;
	}
	private boolean isPositionMarksMatch(int position1, int position2, int position3) {
		return board[position1] == board[position2] &&  board[position1] == board[position3] && board[position1] != EMPTY_POSITION;
	}
	
	private boolean checkRow() {
		if(
			isPositionMarksMatch(0, 3, 6) ||
			isPositionMarksMatch(1, 4, 7) ||
			isPositionMarksMatch(2, 5, 8)) {
			winner = currentPlayer;
			return true;
		}
		return false;
	}
	private boolean checkDiag() {
		if(isPositionMarksMatch(0, 4, 8) || isPositionMarksMatch(2, 4, 6)){
			winner = currentPlayer;
			return true;
		}
		return false;
	}
	private boolean isATie() {
		for(var element : board)
			if(element == EMPTY_POSITION)
				return false;
		return true;
	}
	
	//switch paler
	private void switchPlayer() {
		if(currentPlayer ==P1)
			currentPlayer = P2;
		else currentPlayer = P1;
	}
	
	// Vs Pc __________________________
	private int randomNumber() {
		Random random = new Random();
		int index = random.nextInt(freePositions.size() - 1);
		
		return freePositions.get(index);
	}
	
	
	private void playerVsPc() {
		while(isGameRuuning) {
			playerRound();
			if(isAWin() || isATie()){
				isGameRuuning = false;
				break;
			}
			
			switchPlayer();

			pcRound();
			if(isAWin() || isATie()){
				isGameRuuning = false;
				break;
			}
			switchPlayer();
		}
		result();
	}

	
	private void twoPlayersTicTacToe() {
		while(isGameRuuning) {
			playerRound();
			
			if(isAWin() || isATie()){
				isGameRuuning = false;
				break;
			}
			switchPlayer();
			
			playerRound();
			if(isAWin() || isATie()){
				isGameRuuning = false;
				break;
			}
			switchPlayer();
		}
		result();
		
	}

	private void playerRound() {
		try {
			sc = new Scanner(System.in);
			//take player input
			System.out.println("Player "+ currentPlayer +" turn. \nEnter A number 1-9");
			var input = sc.nextInt();
			playerInputToTheBord(input);
			prindBoard();
		}catch (Exception e) {
			System.out.println("You Enterd Unvalid key @@");
			playerRound();
		}
	}

	private void pcRound() {
		System.out.println("PC Move");
		pcInputToTheBord(randomNumber());
		prindBoard();
	}
	private void result() {
		if(winner == ' ') 
			System.out.print("It's Atie @@");
		else
			System.out.printf("Congratulation to %s You Win!", winner);
		System.err.println();
	}

	private boolean isAWin() {
		return checkDiag() || checkHorizontal() || checkRow();
	}
	
	public static void gameExplanation() {
		System.out.println("Welcome To Tic Tac Toe Game (*_^)");
		System.out.println("For That Game You may choose to play with your friend Or Vs Pc, You will be asked to enter 1 or 2 for play with your friend or with Pc respectivelly.");
		System.out.print("To Mark A position with X for first player or O for second player, You must type the possition number");
		System.out.println("Note: position number are bellow ");
		System.out.println("1 | 2 | 3");
		System.out.println("4 | 5 | 6");
		System.out.println("7 | 8 | 9");
		System.out.println("Remember if you enter invalid possition number you round will be passed");
		System.out.println("Good Luck (^-^)");
		
	}
	public void closeScanner() {
		sc.close();
	}
}
