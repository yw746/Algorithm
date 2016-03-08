import java.util.Scanner;


/** Tic-Tac-Toe: Two-player console, non-graphics, non-OO version.*/
public class TicTacToe {
    private char[][] board;               // game board in 2D array
    private char currentPlayer;           // the current player('x' or 'o')
    private int rows = 3;
    private int columns = 3;
    
    public static Scanner sc = new Scanner(System.in); // the input Scanner
    
    public TicTacToe() {
        board = new char[rows][columns];           
        currentPlayer = 'x';              // 'x' player first
        initializeBoard();
    }
    
    // Set/Reset the board back to all empty values.
    public void initializeBoard() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                board[i][j] = '-';
            }
        }
    }
    
    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("-------------");
        for(int i = 0; i < rows; i++) {
            System.out.print("| ");
            for(int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("");
            System.out.println("-------------");
        }
    }
    
    /** Loop through all cells of the board to see if the board is empty.
      * if one is found to be empty (with value '-'), return false, otherwise 
      * return true. */
    public boolean isBoardFull() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Returns true if there is a win, false otherwise.
    public boolean checkForWinner() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }
    
    // Loop through rows and see if any are winners.
    private boolean checkRows() {
        for (int i = 0; i < rows; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && 
                board[i][0] == board[i][2]) {
                return true;
            }
        }
        return false;
    }
    
    // Loop through rows and see if any are winners.
    private boolean checkColumns() {
        for (int i = 0; i < columns; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && 
                board[0][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }
    
    // Check the two diagonals to see if either is a win.
    private boolean checkDiagonals() {
        return (board[0][0] != '-' && board[0][0] == board[1][1] 
                && board[0][0] == board[2][2]) 
                ||
               (board[0][2] != '-' && board[0][2] == board[1][1] 
                && board[0][2] == board[2][0]);
                
    }
    
    // Change player marks back and forth.
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'x') ? 'o' : 'x';
    }
    
    /** Places a mark at the cell specified by row and col 
      * with the mark of the current player. 
      * Precondition: row and col in range 0-2*/
    public void updateBoard() {
        boolean validInput = false;      // if true, the input is valid
        do {
         if (currentPlayer == 'x') {
            System.out.print("Player 'X', enter your move: ");
         } else {
            System.out.print("Player 'O', enter your move: ");
         }
         int row = sc.nextInt() - 1;  // array index starts at 0 instead of 1
         int col = sc.nextInt() - 1;
         
         if (row >= 0 && row < rows && col >= 0 && col < columns 
             && board[row][col] == '-') {
            board[row][col] = currentPlayer;  // update board
            validInput = true;  // input okay, exit loop
         } else {
            System.out.println("Sorry, the move at (" 
                               + (row + 1) + ", " + (col + 1)
                               + ") is not valid. Please try again");
         }
      } while (!validInput);  // repeat until input is valid
    }
    
    public static void main(String[] args) {
        // Initialize the game-board 
        TicTacToe t = new TicTacToe();
        boolean gameStatus = false;             // if true, one player wins
        // play the game until one player wins
        do {
            t.updateBoard();
            t.printBoard();
            // Print message if game over
            if (t.checkForWinner()) {
                System.out.println("We have a winner! Congratulation!");
                gameStatus = true;
            }
            if (t.isBoardFull()) {
                System.out.println("We have a draw!");
                gameStatus = true;
            }
            // Switch player
            t.changePlayer();  
        } while (gameStatus == false); // repeat until game over.
        
        
    } 
}