package game;

import java.util.Scanner;

public class manager {

	private Player player1 , player2 ;
	private Board board ;
	
	public void startGame() {
		Scanner scan = new Scanner(System.in);
		//player input
		player1 = takePlayerInput(1);
		player2 = takePlayerInput(2);
		
		while(player2.getSymbol() == player1.getSymbol()) {
			System.out.println("please take another symbol");
			char symbol = scan.next().charAt(0);
			player2.setSymbol(symbol);
		}
		
		//create board
		board = new Board(player1.getSymbol(),player2.getSymbol());
		boolean player1turn = true;
		int status =board.INCOMPLETE ;
		while( status == board.INCOMPLETE || status == board.INVALID) {
			if(player1turn) {
				System.out.println("Player1 - " + player1.getName() + "'s turn " );
				System.out.println("enter x : ");
				int x = scan.nextInt();
				System.out.println("enter y : ");
				int y= scan.nextInt();
				status = board.move(player1.getSymbol(),x,y);
				if(status != board.INVALID) {
					player1turn = false;
					board.print();
				}else {
					System.out.println("INVALID MOVE !! TRY AGAIN !!");
				}
			}
			else{
				System.out.println("Player2 - " + player2.getName() + "'s turn " );
				System.out.println("enter x : ");
				int x = scan.nextInt();
				System.out.println("enter y : ");
				int y= scan.nextInt();
				status = board.move(player2.getSymbol(),x,y);
				if(status != board.INVALID) {
					player1turn = true;
					board.print();
				}else {
					System.out.println("INVALID MOVE !! TRY AGAIN !!");
				}
			}
		}
		if(status == board.PLAYER_1_WINS) {
			System.out.println("player 1 "+ player1.getName() +" wins!!");
		}
		
		else if(status == board.PLAYER_2_WINS) {
			System.out.println("player 2 "+ player2.getName() +" wins!!");
		}
		else {
			System.out.println("DRAW!!");
		}
	}
	
	public static Player takePlayerInput(int num) {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter player " + num + " name :" );
		String name = scan.nextLine();
		System.out.println("enter player "+ num +" symbol");
		char symbol = scan.nextLine().charAt(0);
		Player  player = new Player(name , symbol);
		return player;
	}
	
	public static void main(String[] args) {
				
		manager m = new manager();
		m.startGame();
	}

}
