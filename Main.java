import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name = "";
        int gamesPlayed = 0;
        boolean playAgain = true;
        try {
            // Welcome Message
            System.out.println("Welcome to Tic Tac Toe game <3 ;) \n What is your name?");
            name = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Sorry String onlt..");
        }
        try {

            while (playAgain) { //while to count how many times user play
                gamesPlayed++;
                System.out.println("\nGame " + gamesPlayed + ":");

                char[][] board = new char[3][3]; // 2D Array
                boolean gameOver = false;
                boolean playerTurn = true;
                int moveCount = 0;
                System.out.println("Nice to meet you " + name + ", now let's start the game! \n Choose 1 for 'O' or 2 for 'X'?");
                int role = input.nextInt();
                System.out.println("You will play with " + ((role == 1) ? "O" : "X"));
                char computerSymbol = (role == 1) ? 'X' : 'O';

                printBoard(board); // calling method

                System.out.println("Choose the number for the position you want: ");
                int position = input.nextInt();

                // Check if the chosen position is valid and available
                if (position >= 1 && position <= 9 && board[(position - 1) / 3][(position - 1) % 3] == 0) {
                    //  board with the user's chosen position
                    board[(position - 1) / 3][(position - 1) % 3] = (role == 1) ? 'O' : 'X';
                    printBoard(board);
                    moveCount++;
                    playerTurn = !playerTurn;
                } else {
                    System.out.println("Invalid position or position already taken. Please try again.");
                }
                while (!gameOver) {
                    if (!playerTurn) {
                        // Computer's turn
                        int computerPosition = findBestMove(board, computerSymbol);
                        board[(computerPosition - 1) / 3][(computerPosition - 1) % 3] = computerSymbol;
                        printBoard(board);
                        System.out.println("Computer's move: " + computerPosition);
                        moveCount++;
                        playerTurn = !playerTurn;
                    } else {
                        // User's turn
                        System.out.println("Choose the number for the position you want: ");
                        position = input.nextInt();
                        if (position >= 1 && position <= 9 && board[(position - 1) / 3][(position - 1) % 3] == 0) {
                            board[(position - 1) / 3][(position - 1) % 3] = (role == 1) ? 'O' : 'X';
                            printBoard(board);
                            moveCount++;
                            playerTurn = !playerTurn;
                        } else {
                            System.out.println("Invalid position or position already taken. Please try again.");
                        }
                    }
                    // Check for win/draw conditions
                    if (checkWin(board, (role == 1) ? 'O' : 'X') || checkWin(board, computerSymbol)) {
                        gameOver = true;
                        if (checkWin(board, (role == 1) ? 'O' : 'X')) {
                            System.out.println("Congratulations, you won!");
                        } else {
                            System.out.println("The computer won.");
                        }
                    } else if (moveCount == 9) {
                        gameOver = true;
                        System.out.println("It's a draw!");
                    }
                }

                System.out.println("\nDo you want to play again? (y/n)");
                String playAgainInput = input.next();
                playAgain = playAgainInput.equalsIgnoreCase("y");
            }
        } catch (InputMismatchException e) {
            System.out.println("Sorry numbers only.. try again");
            return;
        }

        System.out.println("BYE BYE \n You played a total of " + gamesPlayed + " games.");
    }

    //method to print the board
    static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'O' || board[i][j] == 'X') {
                    System.out.print(board[i][j] + "|");
                } else {
                    System.out.print((i * 3 + j + 1) + "|");
                }
            }
            System.out.println();
        }
    }

    //for computer
    static int findBestMove(char[][] board, char symbol) {
        // (e.g., a simple algorithm that tries to win or block the user)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return i * 3 + j + 1;
                }
            }
        }
        return -1; // No available moves
    }

    // method if win
    static boolean checkWin(char[][] board, char symbol) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        return false;
    }
}