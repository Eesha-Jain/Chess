import java.util.*;
import ChessPieces.*;

public class Player {
    /*white = 0, red = 1*/
    private int side;
    private Piece [] [] board;
    private HashMap<Character, Character> arr;
    private ArrayList<Piece> kings;

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

    public void exitGame(String notation) {
        if (notation.equalsIgnoreCase("exit")) {
            System.out.println("Sorry you had to leave");
            System.out.println("Hope you come back soon!");
            System.exit(0);
        }
    }

    public boolean inputNotValid(String notation) {

        Character [] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        List<Character> numbersList = Arrays.asList(numbers);

        if ((notation.length() != 4 && notation.length() != 5) || (notation.length() == 5 && notation.charAt(4) != 'x') || !arr.containsKey(notation.charAt(0)) || !arr.containsKey(notation.charAt(2)) || !numbersList.contains(notation.charAt(1)) || !numbersList.contains(notation.charAt(3)))
            return true;
        
        return false;
    }

    public int input(String notation, Piece [] [] board, ArrayList<Piece> kings) {
        Scanner scan = new Scanner (System.in);
        this.board = board;

        King k = (King) kings.get(side);
        this.kings = kings;

        if (notation.equalsIgnoreCase("skip"))
            return 0;

        exitGame(notation);
        boolean inputNotValid = inputNotValid(notation);

        Piece p;

        //Preparation for the inCheck Method
        Piece [] [] fakeBoard = new Piece[8][8];
        String notationTemporarry = "";
        
        //If the input isn't valid
        if (!inputNotValid) {
            p = board[Character.getNumericValue(notation.charAt(1)) - 1][Character.getNumericValue(arr.get(notation.charAt(0)))];

            notationTemporarry = "" + (Integer.parseInt(notation.charAt(1) + "") - 1) + arr.get(notation.charAt(0)) + (Integer.parseInt(notation.charAt(3) + "") - 1) + arr.get(notation.charAt(2));

            for (int i = 0; i < board.length; i++)
                fakeBoard[i] = board[i].clone();
            
            fakeBoard = movePiece(notationTemporarry, fakeBoard);
        } else
            p = board[0][0];

        while (inputNotValid ||!persValidate(notation, fakeBoard) || k.inCheck(notation, board, fakeBoard) || !p.valid(side, notation, board) || (notation.contains("x") && checkmate(notation, board) != 1)) {

            System.out.print("\nThat wasn't valid. Enter Your Move: ");
            notation = scan.nextLine();

            if (notation.equalsIgnoreCase("skip"))
                return 0;

            exitGame(notation);
            inputNotValid = inputNotValid(notation);
        
            //If the input isn't valid
            if (!inputNotValid) {
                p = board[Character.getNumericValue(notation.charAt(1)) - 1][Character.getNumericValue(arr.get(notation.charAt(0)))];

                for (int i = 0; i < board.length; i++)
                    fakeBoard[i] = board[i].clone();
                
                fakeBoard = movePiece(notationTemporarry, fakeBoard);

                notationTemporarry = "" + (Integer.parseInt(notation.charAt(1) + "") - 1) + arr.get(notation.charAt(0)) + (Integer.parseInt(notation.charAt(3) + "") - 1) + arr.get(notation.charAt(2));
            } else
                p = board[0][0];
        }

        notation = "" + (Integer.parseInt(notation.charAt(1) + "") - 1) + arr.get(notation.charAt(0)) + (Integer.parseInt(notation.charAt(3) + "") - 1) + arr.get(notation.charAt(2));

        board = movePiece(notation, board);
        
        if (notation.contains("x"))
            return checkmate(notation, board);

        return 0;
    }

    public int checkmate(String notation, Piece [] [] board) {
        //FIX THIS METHOD

        /*int x = Integer.parseInt(notation.charAt(3) + "") - 1;
        int y = Integer.parseInt(arr.get(notation.charAt(2)) + "");

        //initalizing the fakee board
        Piece [] [] fakee = new Piece [8][8];

        for (int i = 0; i < board.length; i++)
            fakee[i] = board[i].clone();
        
        fakee = movePiece(notation, fakee);

        //initalizing the fakeBoard board
        Piece [] [] fakeBoard = new Piece [8][8];

        for (int i = 0; i < board.length; i++)
            fakeBoard[i] = fakee[i].clone();
        
        //checking if king is in checkmate in any blank spot next to him
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y - 1; j++) {
                if (i >= 0 && i < 8 && j >= 0 && j < 8) {
                    String notationTemporarry = y + "" + x + j + i;
                    
                    King kin = (King) kings.get(side);
                    if (!kin.inCheck(notationTemporarry, board, fakeBoard))
                        return 0;
                }
            }
        }*/

        //THING TO DO: Check to see whether any piece can make the checkmate not valid

        return 1;
    }

    public Piece [] [] movePiece(String notation, Piece [] [] board) {

        int xor = Integer.parseInt(notation.charAt(0) + "");
        int xno = Integer.parseInt(notation.charAt(1) + "");
        int yor = Integer.parseInt(notation.charAt(2) + "");
        int yno = Integer.parseInt(notation.charAt(3) + "");

        Piece p = board [xor][xno];

        if (p.getName() == 'P') {
            p = pawnPromotion(p, notation);
        }

        if (p.getName() == 'P') {
            Pawn itsAPawn = (Pawn) p;
            if (itsAPawn.specialCapturingMove()) {
                board[yor - 1][yno] = new Blank();
            }
        }

        if (p.getName() == 'K') {
            castlingWithKing(p, notation);
            King kingVersion = (King) p;
            kingVersion.setPosition((yor+1) + "" + yno);
        }
        
        board[xor][xno] = new Blank();
        board[yor][yno] = p;

        return board;
    }

    public void castlingWithKing(Piece x, String notation) {
        
        King p = (King) x;

        if (p.getCastling() != 0) {
            //0 = not castling, 1 = short side, 2 = long side
            if (p.getCastling() == 1 && side == 0) {
                Piece ba = board[7][7];
                board[7][7] = new Blank();
                board[7][5] = ba;
            } else if (p.getCastling() == 1 && side == 1) {
                Piece ba = board[0][7];
                board[0][7] = new Blank();
                board[0][5] = ba;
            } else if (p.getCastling() == 2 && side == 0) {
                Piece ba = board[7][0];
                board[7][0] = new Blank();
                board[7][3] = ba;
            } else if (p.getCastling() == 2 && side == 1) {
                Piece ba = board[0][0];
                board[0][0] = new Blank();
                board[0][3] = ba;
            }
        }
    }

    public Piece pawnPromotion(Piece p, String notation) {
        Scanner scan = new Scanner (System.in);

        //ArrayList
        List<Character> pieces = new ArrayList<>();
        pieces.add('R');
        pieces.add('N');
        pieces.add('B');
        pieces.add('Q');

        //White promotion
        if (side == 0) {
            if (notation.charAt(2) == '0') {
                System.out.println("Congratulations on pawn promotion! What piece do you want?\nR - Rook\nN - Knight\nB - Bishop\nQ - Queen ");
                String wanted = scan.next();
                char wantedPiece = wanted.charAt(0);

                while (!pieces.contains(wantedPiece)) {
                    System.out.print("Invalid, please enter a valid piece: ");
                    wanted = scan.next();
                    wantedPiece = wanted.charAt(0);
                }

                if (wantedPiece == 'R')
                    p = new Rook(0);
                else if (wantedPiece == 'N')
                    p = new Knight(0);
                else if (wantedPiece == 'B')
                    p = new Bishop(0);
                else
                    p = new Queen(0);
            }
        
        //Black promotion
        } else {
            if (notation.charAt(2) == '7') {
                System.out.println("Congratulations on pawn promotion! What piece do you want?\nR - Rook\nN - Knight\nB - Bishop\nQ - Queen ");
                String wanted = scan.next();
                char wantedPiece = wanted.charAt(0);

                while (!pieces.contains(wantedPiece)) {
                    System.out.print("Invalid, please enter a valid piece: ");
                    wanted = scan.next();
                    wantedPiece = wanted.charAt(0);
                }

                if (wantedPiece == 'R')
                    p = new Rook(1);
                else if (wantedPiece == 'N')
                    p = new Knight(1);
                else if (wantedPiece == 'B')
                    p = new Bishop(1);
                else
                    p = new Queen(1);
            }
        }

        return p;

    }

    public boolean persValidate(String notation, Piece [] [] fakeBoard) {

        String persTemp = notation.charAt(0) + "" + (Integer.parseInt(notation.charAt(1) + "") - 1) + notation.charAt(2) + (Integer.parseInt(notation.charAt(3) + "") - 1);

        for (char c: persTemp.toCharArray()) {
            if (!arr.keySet().contains(c) && !arr.values().contains(c))
                return false;
        }

        Piece temp = board[Integer.parseInt(notation.charAt(1) + "") - 1][Integer.parseInt(arr.get(notation.charAt(0)) + "")];

        if (temp.getName() == '-' || temp.getSide() != side)
            return false;    

        int y = Integer.parseInt(arr.get(notation.charAt(2)) + "");
        int x = Integer.parseInt(notation.charAt(3) + "") - 1;

        if (board[x][y].getName() != '-' && board[x][y].getSide() == side)
            return false; 
        
        /*if (board[x][y].getName() == 'K') {
            King k = (King) board[x][y];
            if (k.inCheck(notation, board, fakeBoard))
                return false;
        }*/

        return true;
    }
}