package LLD.TicTacToe.Games;

import LLD.TicTacToe.Constants.Move;
import LLD.TicTacToe.Constants.Status;
import LLD.TicTacToe.Exceptions.InvalidMoveException;

import java.util.HashSet;
import java.util.List;

import static LLD.TicTacToe.Constants.Status.*;

public class TicTacToe implements Game{

    private Character[][] board;
    public final String player1;
    public final String player2;
    private final int N;
    private static final Character player1Move = 'O';
    private static final Character player2Move = 'X';
    private static int numberOfMoves;
    public boolean player1Turn;
    private static final HashSet<Move> validMoves = new HashSet<>(List.of(Move.ONE, Move.TWO, Move.THREE, Move.FOUR, Move.FIVE, Move.SIX, Move.SEVEN, Move.EIGHT, Move.NINE));

    public TicTacToe(int N, String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Turn = true;
        this.N = N;
        board = new Character[N][N];
        numberOfMoves = 0;
    }

    public Status makeMove(Move move) {
        if(!validMoves.contains(move)) {
            throw new InvalidMoveException("This move is not valid");
        }
        String position = move.getPosition();
        int r = position.charAt(0) - '0';
        int c = position.charAt(1) - '0';
        if(board[r][c] != null) {
            // throw new InvalidMoveException("This position is already filled");
            showBoard();
            return INVALID_MOVE;
        }
        if(player1Turn) {
            board[r][c] = player1Move;
        } else {
            board[r][c] = player2Move;
        }
        numberOfMoves++;
        Status currStatus = getStatus();
        player1Turn = !player1Turn;
        showBoard();
        return currStatus;
    }

    private Status getStatus() {
        Character moveToCheck = player1Turn ? player1Move : player2Move;
        // check in rows
        for(int i=0; i<N; i++) {
            int count = 0;
            for(int j=0; j<N; j++) {
                if(board[i][j] == moveToCheck) count++;
            }
            if(count == N) {
                return player1Turn ? PLAYER1 : PLAYER2;
            }
        }
        // check in cols
        for(int j=0; j<N; j++) {
            int count = 0;
            for(int i=0; i<N; i++) {
                if(board[i][j] == moveToCheck) count++;
            }
            if(count == N) {
                return player1Turn ? PLAYER1 : PLAYER2;
            }
        }
        // check in left diag
        int i=0, j=0, count=0;
        while(i<N && j<N) {
            if(board[i][j] == moveToCheck) count++;
            i++;
            j++;
        }
        if(count == N) {
            return player1Turn ? PLAYER1 : PLAYER2;
        }
        // check in right diag
        i=N-1; j=0; count=0;
        while(i>=0 && j<N) {
            if(board[i][j] == moveToCheck) count++;
            i--;
            j++;
        }
        if(count == N) {
            return player1Turn ? PLAYER1 : PLAYER2;
        }

        return numberOfMoves == N*N ? DRAW : IN_PROGRESS;
    }

    public String getResult(Status status) {
        if(DRAW.equals(status)) {
            return "It is a draw!";
        }
        return (PLAYER1.equals(status) ? player1 : player2) + " wins!";
    }

    private void showBoard() {
        for(int i=0; i<N; i++) {
            System.out.print("[ ");
            for(int j=0; j<N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("]");
        }
    }

}
