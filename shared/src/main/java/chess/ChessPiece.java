package chess;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece other = (ChessPiece) o;
        return other.pieceColor == pieceColor && other.type == type;
    }

    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    public String toString() {
        return "Team color: " + pieceColor + "\nPiece type: "+ type;
    }

    // -1 = Out of bounds
    //  0 = Occupied by same team
    //  1 = Occupied by other team
    //  2 = Not occupied
    private int checkPosition(ChessBoard board, ChessPosition newPosition) {
        if (newPosition.getRow() < 1 || newPosition.getRow() > 8 || newPosition.getColumn() < 1 || newPosition.getColumn() > 8) {
            return -1;
        }
        if (board.getPiece(newPosition) == null) {
            return 2;
        }
        if (board.getPiece(newPosition).getTeamColor() == pieceColor) {
            return 0;
        } else {
            return 1;
        }
    }
    private ArrayList<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> result = new ArrayList<ChessMove>();
        int myRow = myPosition.getRow();
        int myCol = myPosition.getColumn();
        ChessPosition newPos = new ChessPosition(myRow + 1, myCol - 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow + 1, myCol);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow + 1, myCol + 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow, myCol - 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow, myCol + 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow - 1, myCol - 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow - 1, myCol);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow - 1, myCol + 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        return result;
    }
    private ArrayList<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> result = new ArrayList<ChessMove>();
        int myRow = myPosition.getRow();
        int myCol = myPosition.getColumn();
        int rowTemp = myRow - 1;
        int colTemp = myCol - 1;
        ChessPosition newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp--;
            colTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow + 1;
        colTemp = myCol - 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp++;
            colTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow + 1;
        colTemp = myCol + 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp++;
            colTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow - 1;
        colTemp = myCol + 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp--;
            colTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow - 1;
        colTemp = myCol;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow;
        colTemp = myCol - 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            colTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow + 1;
        colTemp = myCol;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow;
        colTemp = myCol + 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            colTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        return result;
    }
    private ArrayList<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> result = new ArrayList<ChessMove>();
        int myRow = myPosition.getRow();
        int myCol = myPosition.getColumn();
        int rowTemp = myRow - 1;
        int colTemp = myCol - 1;
        ChessPosition newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp--;
            colTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow + 1;
        colTemp = myCol - 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp++;
            colTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow + 1;
        colTemp = myCol + 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp++;
            colTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow - 1;
        colTemp = myCol + 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp--;
            colTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        return result;
    }
    private ArrayList<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> result = new ArrayList<ChessMove>();
        int myRow = myPosition.getRow();
        int myCol = myPosition.getColumn();
        ChessPosition newPos = new ChessPosition(myRow + 2, myCol - 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow + 2, myCol + 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow + 1, myCol - 2);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow + 1, myCol + 2);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow - 1, myCol - 2);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow - 1, myCol + 2);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow - 2, myCol - 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        newPos = new ChessPosition(myRow - 2, myCol + 1);
        if (checkPosition(board, newPos) >= 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        return result;
    }
    private ArrayList<ChessMove> rookMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> result = new ArrayList<ChessMove>();
        int myRow = myPosition.getRow();
        int myCol = myPosition.getColumn();
        int rowTemp = myRow - 1;
        int colTemp = myCol;
        ChessPosition newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow;
        colTemp = myCol - 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            colTemp--;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow + 1;
        colTemp = myCol;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            rowTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        rowTemp = myRow;
        colTemp = myCol + 1;
        newPos = new ChessPosition(rowTemp, colTemp);
        while (checkPosition(board, newPos) == 2) {
            result.add(new ChessMove(myPosition, newPos, null));
            colTemp++;
            newPos = new ChessPosition(rowTemp, colTemp);
        }
        if (checkPosition(board, newPos) == 1) {
            result.add(new ChessMove(myPosition, newPos, null));
        }
        return result;
    }
    private ArrayList<ChessMove> pawnMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> result = new ArrayList<ChessMove>();
        int myRow = myPosition.getRow();
        int myCol = myPosition.getColumn();
        ChessPosition newPos = new ChessPosition(myRow, myCol);
        if (pieceColor == ChessGame.TeamColor.WHITE) {
            newPos = new ChessPosition(myRow + 1, myCol);
            if (checkPosition(board, newPos) == 2) {
                if (myRow == 7) {
                    result.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                    result.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                    result.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                    result.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                } else {
                    result.add(new ChessMove(myPosition, newPos, null));
                }
                if (myRow == 2) {
                    newPos = new ChessPosition(myRow + 2, myCol);
                    if (checkPosition(board, newPos) == 2) {
                        result.add(new ChessMove(myPosition, newPos, null));
                    }
                }
            }
            newPos = new ChessPosition(myRow + 1, myCol - 1);
            if (checkPosition(board, newPos) == 1) {
                if (myRow == 7) {
                    result.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                    result.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                    result.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                    result.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                } else {
                    result.add(new ChessMove(myPosition, newPos, null));
                }
            }
            newPos = new ChessPosition(myRow + 1, myCol + 1);
            if (checkPosition(board, newPos) == 1) {
                if (myRow == 7) {
                    result.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                    result.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                    result.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                    result.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                } else {
                    result.add(new ChessMove(myPosition, newPos, null));
                }
            }
        } else {
            newPos = new ChessPosition(myRow - 1, myCol);
            if (checkPosition(board, newPos) == 2) {
                if (myRow == 2) {
                    result.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                    result.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                    result.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                    result.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                } else {
                    result.add(new ChessMove(myPosition, newPos, null));
                }
                if (myRow == 7) {
                    newPos = new ChessPosition(myRow - 2, myCol);
                    if (checkPosition(board, newPos) == 2) {
                        result.add(new ChessMove(myPosition, newPos, null));
                    }
                }
            }
            newPos = new ChessPosition(myRow - 1, myCol - 1);
            if (checkPosition(board, newPos) == 1) {
                if (myRow == 2) {
                    result.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                    result.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                    result.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                    result.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                } else {
                    result.add(new ChessMove(myPosition, newPos, null));
                }
            }
            newPos = new ChessPosition(myRow - 1, myCol + 1);
            if (checkPosition(board, newPos) == 1) {
                if (myRow == 2) {
                    result.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                    result.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                    result.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                    result.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                } else {
                    result.add(new ChessMove(myPosition, newPos, null));
                }
            }
        }
        return result;
    }
    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        switch (type) {
            case KING:
                return kingMoves(board, myPosition);
            case QUEEN:
                return queenMoves(board, myPosition);
            case BISHOP:
                return bishopMoves(board, myPosition);
            case KNIGHT:
                return knightMoves(board, myPosition);
            case ROOK:
                return rookMoves(board, myPosition);
            case PAWN:
                return pawnMoves(board, myPosition);
            default:
                return null;
        }
    }
}
