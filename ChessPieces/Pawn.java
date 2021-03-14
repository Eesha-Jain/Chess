package ChessPieces;

import java.util.Scanner;
import java.util.HashMap;

public class Pawn extends Piece {

    private final char NAME = 'P';
    private boolean isMoved;
    private boolean justMovedTwo;
    private boolean specialCapturingMove;
    private int side;
    private HashMap<Character, Character> arr;

    public Pawn(int side) {
        isMoved = false;
        this.side = side;
        justMovedTwo = false;
        specialCapturingMove = false;

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

    public boolean justMovedTwo() {
        return justMovedTwo;
    }

    public boolean specialCapturingMove() {
        return specialCapturingMove;
    }
    
    public boolean valid(int side, String notation, Piece [] [] board) {

        if (this.side != side)
            return false;

        justMovedTwo = false;
        specialCapturingMove = false;
        
        int y = Integer.parseInt(arr.get(notation.charAt(2)) + "");
        int x = Integer.parseInt(notation.charAt(3) + "") - 1;

        if (side == 0) {   

            //Capturing Pieces (including special move)         
            if (Math.abs(notation.charAt(0) - notation.charAt(2)) == 1) {

                if ((notation.charAt(1) - notation.charAt(3) == 1) && board[x][y].getName() != '-')
                    return true;
                else if ((notation.charAt(1) - notation.charAt(3) == 1) && board[x][y].getName() == '-' && board[x-1][y].getName() == 'P') {
                    Pawn pa = (Pawn) board[x-1][y];
                    if (pa.justMovedTwo() == true) {
                        specialCapturingMove = true;
                        return true;
                    } else
                        return false;
                } else 
                    return false;
            }

            if (notation.charAt(2) - notation.charAt(0) != 0)
                return false;

            if (board[x][y].getName() != '-')
                return false;

            if (notation.charAt(1) - notation.charAt(3) == 1) {
                isMoved = true;
                return true;
            }

            if (board[x+1][y].getName() != '-')
                return false;
        
            if (!isMoved && notation.charAt(1) - notation.charAt(3) == 2) {
                isMoved = true;
                justMovedTwo = true;
                return true;
            }
        } else {
            if (Math.abs(notation.charAt(2) - notation.charAt(0)) == 1) {
                if ((notation.charAt(3) - notation.charAt(1) == 1) && board[x][y].getName() != '-')
                    return true;
                else if ((notation.charAt(3) - notation.charAt(1) == 1) && board[x][y].getName() == '-' && board[x-1][y].getName() == 'P') {
                    Pawn pa = (Pawn) board[x-1][y];
                    if (pa.justMovedTwo() == true) {
                        specialCapturingMove = true;
                        return true;
                    } else
                        return false;
                } else 
                    return false;
            }

            if (notation.charAt(0) - notation.charAt(2) != 0)
                return false;
            
            if (board[x][y].getName() != '-')
                return false;

            if (notation.charAt(3) - notation.charAt(1) == 1) {
                isMoved = true;
                return true;
            }

            if (board[x-1][y].getName() != '-')
                return false;
        
            if (!isMoved && notation.charAt(3) - notation.charAt(1) == 2) {
                isMoved = true;
                justMovedTwo = true;
                return true;
            }
        }

        return false;
    }
}