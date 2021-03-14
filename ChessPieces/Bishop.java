package ChessPieces;

import java.util.Scanner;
import java.util.HashMap;

public class Bishop extends Piece {

    private final char NAME = 'B';
    private int side;
    private HashMap<Character, Character> arr;
    
    public Bishop(int side) {
        this.side = side;

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

    public char getName() {
        return NAME;
    }
    
    public int getSide() {
        return side;
    }

    public boolean valid(int side, String notation, Piece [] [] board) {
        if (this.side != side)
            return false;

        //Variable declarations
        int yaf = Integer.parseInt(arr.get(notation.charAt(2)) + "");
        int xaf = Integer.parseInt(notation.charAt(3) + "") - 1;
        int yorg = Integer.parseInt(arr.get(notation.charAt(0)) + "");
        int xorg = Integer.parseInt(notation.charAt(1) + "") - 1;
        
        //Diagonal movements
        if (Math.abs(notation.charAt(0) - notation.charAt(2)) == Math.abs(notation.charAt(1) - notation.charAt(3))) {

            int xminn = Math.min(xorg, xaf);
            int xmaxx = Math.max(xorg, xaf);
            int yminn = Math.min(yorg, yaf);
            int ymaxx = Math.max(yorg, yaf);

            int changing = Math.abs(notation.charAt(0) - notation.charAt(2));

            if ((notation.charAt(0) - notation.charAt(2)) == (notation.charAt(1) - notation.charAt(3))) {
                for (int i = 1; i < changing; i++) {
                    if (board[xminn + i][yminn + i].getName() != '-')
                        return false;
                }
            } else {
                for (int i = 1; i < changing; i++) {
                    if (board[xminn + i][ymaxx - i].getName() != '-')
                        return false;
                }
            }

            return true;
        }

        return false;
    }
}