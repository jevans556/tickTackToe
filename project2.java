import java.util.Scanner;
import java.util.Random;
public class project2 {

    public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		char[][] gameBoard = new char[3][3];
		int ans;
		boolean gameOver;
		Random randNum = new Random();
		int numTurns;
		final int maxNumTurns = 9;
	/*start loop to ask the user if they want to play a game of tick tak toe. The user
	each iteration of the loop will initialize the game  board and number of turns so a new game
	can be played
	 */
		do {
			System.out.print("Would you like to play game of tick tack toe? 1 for yes, 2 for no ");
			ans = scnr.nextInt();
			if((ans != 1) && (ans != 2)){
				System.out.println("that is an invalid input");
			}
			if(ans == 1) { // initialize game board and number of turns
				System.out.println("Starting game");
				System.out.println("You are: X...");
				initializeGameBoard(gameBoard);
				numTurns = 0;
				gameOver = false;
				do {   // start of loop that will handle the logic of the game
					printGameBoard(gameBoard);
					getMove(scnr, gameBoard);
					printGameBoard(gameBoard);
					numTurns++; // counts the number of turns to make sure that the max number of turns
								//has not been  made yet

					gameOver = determineDiagonalWinner(gameOver, gameBoard);
					gameOver = determineHorizontalWinner(gameOver,gameBoard);
					gameOver = determineVerticalWinner(gameOver,gameBoard);

					// check to see if there still moves to be made before the opponent moves
					if((!gameOver) && (numTurns < maxNumTurns)) {
						opponentMove(gameBoard, randNum);
						printGameBoard(gameBoard);
						numTurns++;

						gameOver = determineDiagonalWinner(gameOver, gameBoard);
						gameOver = determineHorizontalWinner(gameOver,gameBoard);
						gameOver = determineVerticalWinner(gameOver,gameBoard);
					}
					// if the game has  not been won yet and there are no more moves left then
					// this will execute and end the game
					if((numTurns == maxNumTurns) && (!gameOver)){
						System.out.println("There are no more moves left");
						gameOver =  true;
					}
					if(gameOver){
						System.out.println("Game over");
					}

				} while (!gameOver);
			}


			} while (ans != 2); // prints out goodbye if the user doesn't want to play
			System.out.println("Goodbye");


		}
		public static void initializeGameBoard ( char[][] board){
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					board[i][j] = '-';
				}
			}
		}
		public static void printGameBoard ( char[][] board){
			for (int i = 0; i < 3; i++) {
				System.out.println();
				for (int j = 0; j < 3; j++) {
					System.out.print(board[i][j] + " | ");
				}
			}
			System.out.println();
		}
		/* gets player move from the user by using a string and a switch statement. also checks to see if
		the move if available and that the input is correct. If not, then it will ask the user to re enter the
		desired move
		 */
		public static void getMove (Scanner scan,char[][] board){
			String move;
			boolean validMove = false;
			System.out.println("Please select your move...");
			System.out.println("Press tl for top left, tm for top middle, tr for top right");
			System.out.println("Press ml for middle left, m for middle, mr for middle right");
			System.out.println("Press ll for lower left lm for lower middle and lr for lower right...");
			do {

				move = scan.next();

				switch (move) {
					case "tl":
						if (board[0][0] == '-') {
							board[0][0] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "tm":
						if (board[0][1] == '-') {
							board[0][1] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "tr":
						if (board[0][2] == '-') {
							board[0][2] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "ml":
						if (board[1][0] == '-') {
							board[1][0] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "m":
						if (board[1][1] == '-') {
							board[1][1] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "mr":
						if (board[1][2] == '-') {
							board[1][2] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "ll":
						if (board[2][0] == '-') {
							board[2][0] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "lm":
						if (board[2][1] == '-') {
							board[2][1] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					case "lr":
						if (board[2][2] == '-') {
							board[2][2] = 'x';
							validMove = true;
						} else {
							System.out.println("That move is already taken, Please try again");
						}
						break;
					default:
						System.out.println("That is an invalid selection. Please enter a valid selection");
						break;
				}
			}while(!validMove);


		}
		/* generates a random move by the computer. A random number between 0 and 2 are generated and passed
		into the array. It will check to see if the move is not already taken. If the move is not available,
		it will keep generating random numbers until a move is possible
		 */
		public static void opponentMove(char[][] board, Random rand){
    	int i, j;
    	boolean goodMove = false;

    	System.out.println("Opponent's move...");
    	do {

			j = rand.nextInt(3);
			i = rand.nextInt(3);

			if(board[i][j] == '-') {
				board[i][j] = 'o';
				goodMove = true;
			}
		}while(!goodMove);
		}
		/*each method used to determine the winner check each row and column to see if they all match
		If there is a match, it checks to make sure that they are either x's or o's. If they are, then
		it sets gameWon to true and prints out a win message. otherwise gameWon remains false and then
		gets returned by the method.
		 */
		public static boolean determineHorizontalWinner(boolean gameWon, char[][]board) {
			if ((board[0][0] == (board[0][1])) && (board[0][1] == board[0][2])) {
				if ((board[0][0] == 'x') || (board[0][0] == 'o')) {
					gameWon = true;
					System.out.println("Horizontal win");
				}
			}
			if ((board[1][0] == (board[1][1])) && (board[1][1] == board[1][2])) {
				if ((board[1][0] == 'x') || (board[1][0] == 'o')) {
					gameWon = true;
					System.out.println("Horizontal win");
				}
			}
			if ((board[2][0] == (board[2][1])) && (board[2][1] == board[2][2])) {
				if ((board[2][1] == 'x') || (board[2][1] == 'o')) {
					gameWon = true;
					System.out.println("Horizontal win");
				}
			}

			return gameWon;
		}
	public static boolean determineVerticalWinner(boolean gameWon, char[][]board) {
		if ((board[0][0] == (board[1][0])) && (board[1][0] == board[2][0])) {
			if ((board[0][0] == 'x') || (board[0][0] == 'o')) {
				gameWon = true;
				System.out.println("Vertical win");
			}
		}
		if ((board[0][1] == (board[1][1])) && (board[1][1] == board[2][1])) {
			if ((board[2][1] == 'x') || (board[2][1] == 'o')) {
				gameWon = true;
				System.out.println("Vertical win");
			}
		}
		if ((board[0][2] == (board[1][2])) && (board[1][2] == board[2][2])) {
			if ((board[0][2] == 'x') || (board[0][2] == 'o')) {
				gameWon = true;
				System.out.println("Vertical win");
			}
		}
		return gameWon;
	}
	public static boolean determineDiagonalWinner(boolean gameWon, char[][]board){
		if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0])){
				if((board[0][2] == 'x') || (board[0][2] == 'o')){
					gameWon = true;
					System.out.println("diagonal win");
				}
			}
		if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])){
				if((board[0][0] == 'x') || (board[0][0] == 'o')){
					gameWon = true;
					System.out.println("diagonal win");
				}
			}
		return gameWon;
    }
	}
	/* Answer to part 2
		There is one main difference between using the pre-fix operator and the post-fix operator. An example of a
	pre-fix operator is ++i, and an example of a post-fix operator is i++. The pre-fix operator will increment
	the value and then assign it to the what the value is is being assigned to. For example, if i is 1 and
	there is a statement i = 1 + (++i), i will be incremented and then added to one and then assigned to i,
	making the value 3. For the post-fix operator, if the statement is i = 1 + i++, then i is assigned 1
	plus the current value of i and then i get incremented after the assignment, thus i will get assigned 2
	and then i will get incremented.
	 */

	/* Answer to part 3
		The use of static in methods allows us to call a method without creating an instance of a class. For
	example. If there is a car class and a method to determine the miles per hour. A programmer has to create
	a new car object to call the miles per hour method in order to use it if it is not static. If the method
	were declared as static, then one does not have to create a new car object in order to ise it. It can be
	useful when a method can be used with any particular instance of a class instead of just one particular
	object.

	sources:
	https://www.geeksforgeeks.org/difference-between-static-and-non-static-method-in-java/
	https://stackoverflow.com/questions/3903537/what-is-the-difference-between-a-static-method-and-a-non-static-method
	 */



