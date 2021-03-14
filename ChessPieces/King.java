package ChessPieces;

import java.util.*;

public class King extends Piece {

    private final char NAME = 'K';
    private boolean isMoved;
    private int side;
    private int castling;
    private HashMap<Character, Character> arr;
    private String position;

    public King(int side) {
        isMoved = false;
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

        if (side == 0)
            this.position = "e8";
        else
            this.position = "e1";
    }

    public char getName() {
        return NAME;
    }

    public int getSide() {
        return side;
    }

    public int getCastling() {
        return castling;
    }

    public void setPosition(String pos) {
        String temp = pos.charAt(1) + "" + pos.charAt(0);

        for (char key: arr.keySet()) {
            if (arr.get(key) == temp.charAt(0)) {
                temp = key + "" + temp.charAt(1);
                break;
            }
        }

        this.position = temp;
    }

    public boolean validSpace(int x, int y) {
        if (x < 0 || x > 8 || y < 0 || y > 8)
            return false;
        return true;
    }

    public boolean inCheck(String notation, Piece [] [] board, Piece [] [] fakeBoard) {
        //Variable declarations
        int xaf = Integer.parseInt(notation.charAt(3) + "") - 1;
        int yaf = Integer.parseInt(arr.get(notation.charAt(2)) + "");

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c].getName() != '-' && board[r][c].getSide() != side) {
                    String xe = "";
                    String cChar = c + "";

                    for (char key: arr.keySet()) {
                        if (arr.get(key) == cChar.charAt(0)) {
                            xe += key + "";
                            break;
                        }
                    }

                    String temp = xe + (r+1) + this.position;
                    
                    if (board[r][c].valid(board[r][c].getSide(), temp, fakeBoard))
                        return true;
                }
            }
        }
        
        return false;
    }

    public boolean validCheck2(String notation, Piece [] [] board) {
        int x = 0;
        if (side == 0) x = 7;
        
        Piece [] [] fakeBoard = new Piece [8][8];
        for (int i = 0; i < fakeBoard.length; i++)
            fakeBoard[i] = board[i].clone();

        King methodKing = (King) fakeBoard[x][4];

        Piece temp = fakeBoard[x][5];
        fakeBoard[x][5] = fakeBoard[x][4];
        fakeBoard[x][4] = temp;

        if (methodKing.inCheck(notation, board, fakeBoard))
            return false;
        
        temp = fakeBoard[x][6];
        fakeBoard[x][6] = fakeBoard[x][5];
        fakeBoard[x][5] = temp;

        if (methodKing.inCheck(notation, board, fakeBoard))
            return false;
        
        return true;
    }

    public boolean validCheck3(String notation, Piece [] [] board) {
        int x = 0;
        if (side == 0) x = 7;
        
        Piece [] [] fakeBoard = new Piece [8][8];
        for (int i = 0; i < fakeBoard.length; i++)
            fakeBoard[i] = board[i].clone();
        
        King methodKing = (King) fakeBoard[x][4];


        Piece temp = fakeBoard[x][1];
        fakeBoard[x][1] = fakeBoard[x][4];
        fakeBoard[x][4] = temp;

        if (methodKing.inCheck(notation, board, fakeBoard))
            return false;
        
        temp = fakeBoard[x][2];
        fakeBoard[x][2] = fakeBoard[x][1];
        fakeBoard[x][1] = temp;

        if (methodKing.inCheck(notation, board, fakeBoard))
            return false;
        

        temp = fakeBoard[x][3];
        fakeBoard[x][3] = fakeBoard[x][2];
        fakeBoard[x][2] = temp;

        if (methodKing.inCheck(notation, board, fakeBoard))
            return false;
        
        return true;
    }
    
    public boolean valid(int side, String notation, Piece [] [] board) {
        if (this.side != side)
            return false;

        castling = 0;

        //Castling
        if (!isMoved) {
            isMoved = true;
            //Short Castle
            if (notation.charAt(0) == 'e' && notation.charAt(2) == 'g') {
                //white
                if (side == 0) {
                    if (board[7][5].getName() == '-' && board[7][6].getName() == '-' && board[7][7].getName() == 'R')
                        if (board[7][7].isMoved() == false && validCheck2(notation, board)) {
                            castling = 1;
                            return true;
                        }
                } else { //red
                    if (board[0][5].getName() == '-' && board[0][6].getName() == '-' && board[0][7].getName() == 'R')
                        if (board[0][7].isMoved() == false && validCheck2(notation, board)) {
                            castling = 1;
                            return true;
                        }
                }
            //Long Queen Side Castle
            } if (notation.charAt(0) == 'e' && notation.charAt(2) == 'c') {
                //white
                if (side == 0) {
                    if (board[7][1].getName() == '-' && board[7][2].getName() == '-' && board[7][3].getName() == '-' && board[7][0].getName() == 'R')
                        if (board[7][0].isMoved() == false && validCheck3(notation, board)) {
                            castling = 2;
                            return true;
                        }
                } else { //red
                    if (board[0][1].getName() == '-' && board[0][2].getName() == '-' && board[0][3].getName() == '-' && board[0][0].getName() == 'R')
                        if (board[0][0].isMoved() == false && validCheck3(notation, board)) {
                            castling = 2;
                            return true;
                        }
                }
            }
        }

        isMoved = true;

        //Diagonal move
        if (Math.abs(notation.charAt(0) - notation.charAt(2)) == 1 && Math.abs(notation.charAt(1) - notation.charAt(3)) == 1) {
            return true;
        }

        //Straight move
        if ((Math.abs(notation.charAt(0) - notation.charAt(2)) == 0 && Math.abs(notation.charAt(1) - notation.charAt(3)) == 1) || (Math.abs(notation.charAt(1) - notation.charAt(3)) == 0 && Math.abs(notation.charAt(0) - notation.charAt(2)) == 1))
            return true;


        return false;
    }
}