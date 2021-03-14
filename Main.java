import java.util.*;
import ChessPieces.*;

class Main {

    private static Piece [] [] board;
    private static ArrayList<Piece> kings;

    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);

        board = new Piece [8] [8];
        kings = new ArrayList<>();
        initalizeBoard();
        welcome();

        String current = "White";

        Player white = new Player(0, board);
        Player red = new Player(1, board);

        int won = 0;

        while (won == 0) {
            System.out.println(current + " player turn.\n\n");

            printBoard();

            System.out.print("\n\nEnter Your Move: ");
            String input = scan.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Sorry you had to leave");
                System.out.println("Hope you come back soon!");
                System.exit(0);
                
            }

            if (current.equals("White"))
                won = white.input(input, board, kings);
            else
                won = red.input(input, board, kings);

            if (won == 0) {
                if (current == "White") 
                    current = "Red";
                else 
                    current = "White";
            }

            System.out.print(ConsoleColors.RESET);
        }

        System.out.println("Congratulations " + current + " for winning!");
    }

    public static void printBoard() {
        char [] temp = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int i = 0; i < 8; i++) {
            System.out.print(ConsoleColors.YELLOW_BOLD + (i+1) + "  ");
            for (int j = 0; j < 8; j++) {
                char c = board[i][j].getName();
                if (board[i][j].getSide() == 0)
                    System.out.print(ConsoleColors.WHITE_BOLD);
                else if (board[i][j].getSide() == 1)
                    System.out.print(ConsoleColors.RED_BOLD);
                else
                    System.out.print(ConsoleColors.GREEN_BOLD);

                System.out.print(c + "  ");
            }            
            System.out.println();
        }

        System.out.print("   ");

        for (int i = 0; i < 8; i++) {
            System.out.print(ConsoleColors.YELLOW_BOLD + temp[i] + "  ");
        }

        System.out.print(ConsoleColors.RESET);
    }

    public static void initalizeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(ConsoleColors.RESET);
                board[i][j] = new Blank();
            }
        }

        for (int i = 0; i < 8; i++) {
            System.out.print(ConsoleColors.RED_BOLD);
            board[1][i] = new Pawn(1);
            System.out.print(ConsoleColors.WHITE_BOLD);
            board[6][i] = new Pawn(0);
        }

        /*Red is far side of board, white is close side of board*/

        System.out.print(ConsoleColors.RED_BOLD);
        board[0][0] = new Rook(1);
        board[0][7] = new Rook(1);
        board[0][1] = new Knight(1);
        board[0][6] = new Knight(1);
        board[0][2] = new Bishop(1);
        board[0][5] = new Bishop(1);
        board[0][3] = new Queen(1);
        board[0][4] = new King(1);


        System.out.print(ConsoleColors.WHITE_BOLD);
        board[7][5] = new Bishop(0);
        board[7][2] = new Bishop(0);
        board[7][6] = new Knight(0);
        board[7][1] = new Knight(0);       
        board[7][7] = new Rook(0);
        board[7][0] = new Rook(0);
        board[7][3] = new Queen(0);
        board[7][4] = new King(0);

        kings.add(board[7][4]); // white
        kings.add(board[0][4]); // red

        System.out.print(ConsoleColors.RESET);
    }

    public static void welcome() {
        System.out.println(ConsoleColors.RESET + "--------------------------------\nWelcome to the Chess Game!\n");
        System.out.println("This is a two player game, where both players are inputting their game pieces on the same computer.");
        System.out.println("When inputting responses:");
        notationrules();
        
        System.out.println("\n");
    }

    public static void notationrules() {
        System.out.println("- Write the position of the piece you want to move, and then write the position you want it to move to.");

        System.out.println("- If the move is checkmate, add an x at the end of the move.");
        System.out.println("- Type \"exit\" to leave the game early.");
        System.out.println("- Type \"skip\" to skip your turn.");
    }
}