import java.util.Scanner;
import java.util.HashMap;

public class Player {
    /*white = 0, red = 1*/
    private int side;
    private Piece [] [] board;
    private HashMap<Character, Character> arr;

    public Player(int side, Piece [] [] board) {
        this.side = side;
        this.board = board;

        arr = new HashMap<>() {{
            put('a', '0');
            put('b', '1');
            put('c', '2');
            put('d', '3');
            put('e', '4');
            put('f', '5');
            put('g', '6');
            put('h', '7');
        }};
    }

    public int input(String notation, Piece [] [] board) {
        Scanner scan = new Scanner (System.in);

        this.board = board;

        Piece p = board[Character.getNumericValue(notation.charAt(1)) - 1][Character.getNumericValue(arr.get(notation.charAt(0)))];

        while ((notation.length() != 4 && notation.length() != 5) || !persValidate(notation) || !p.valid(side, notation, board) || (notation.contains("x") && !checkmate(notation, board))) {

            System.out.print("\nThat wasn't valid. Enter Your Move: ");
            notation = scan.nextLine();

            if (notation.equalsIgnoreCase("exit")) {
                System.out.println("Sorry you had to leave");
                System.out.println("Hope you come back soon!");
                System.exit(0);
                
            }

            p = board[Integer.parseInt(notation.charAt(1) + "") - 1][Integer.parseInt(arr.get(notation.charAt(0)) + "")];
        }

        notation = "" + (Integer.parseInt(notation.charAt(1) + "") - 1) + arr.get(notation.charAt(0)) + (Integer.parseInt(notation.charAt(3) + "") - 1) + arr.get(notation.charAt(2));

        board = movePiece(notation, board);
        
        if (notation.contains("x"))
                return 1;

        return 0;
    }

    public boolean checkmate(String notation, Piece [] [] board) {
        return true;
    }

    public Piece [] [] movePiece(String notation, Piece [] [] board) {

        int xor = Integer.parseInt(notation.charAt(0) + "");
        int xno = Integer.parseInt(notation.charAt(1) + "");
        int yor = Integer.parseInt(notation.charAt(2) + "");
        int yno = Integer.parseInt(notation.charAt(3) + "");

        Piece p = board [xno][xor];
        board[xno][xor] = new Blank();
        board[yno][yor] = p;

        return board;
    }

    public boolean persValidate(String notation) {
        for (char c: notation.toCharArray()) {
            if (!arr.keySet().contains(c) && !arr.values().contains(c))
                return false;
        }

        Piece temp = board[Integer.parseInt(notation.charAt(1) + "") - 1][Integer.parseInt(arr.get(notation.charAt(0)) + "")];

        if (temp.getName() == '-' || temp.getSide() != side)
            return false;     

        return true;
    }
}