package LLD.TicTacToe.Games;

import LLD.TicTacToe.Constants.Move;
import LLD.TicTacToe.Constants.Status;

public interface Game {

    Status makeMove(Move move);

    String getResult(Status status);

}
