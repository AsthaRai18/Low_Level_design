package chess_game;
import java.util.*;
enum Color {
    WHITE, BLACK;
}
abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean isValidMove(Board board, Move move);
}
class Board {
    private Cell[][] cells;

    public Board() {
        // cells = new Cell[8][8];
        // for (int i = 0; i < 8; i++) {
        //     for (int j = 0; j < 8; j++) {
        //         cells[i][j] = new Cell(i, j);
        //     }
        // }
        cells = new Cell[8][8];
        for(int i = 0;i<8;i++)
        {
            for(int j = 0;j<8;j++)
            {
                cells[i][j] = new Cell(i,j);
            }
        }
    }

    public void initializeBoard() {
    }

    public boolean isCheckmate(Player player) {
        // Implement logic to checkmate
        return false;
    }
}
class Cell {
    private int row, col;
    private Piece piece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public void placePiece(Piece piece) {
        this.piece = piece;
    }
}
class Move {
    private Piece piece;
    private Cell start, end;

    public Move(Piece piece, Cell start, Cell end) {
        this.piece = piece;
        this.start = start;
        this.end = end;
    }

    public boolean isCastlingMove() {
        // Implement castling logic
        return false;
    }
    
    public Piece getPiece() {
        return piece;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }
}

class Player {
    private String name;
    private Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public boolean makeMove(Move move) {
        return move.getPiece().isValidMove(null, move);
    }
}

enum GameStatus {
    ACTIVE, CHECKMATE, STALEMATE, DRAW, RESIGNATION;
}
public class chessgame {
    String str = "one|two|three";
    String[] parts = str.split("\\|"); // Escape "|"

}
class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        // Implement logic for king movement
        return true;
    }
}

class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        // Implement logic for queen movement
        return true;
    }
}

