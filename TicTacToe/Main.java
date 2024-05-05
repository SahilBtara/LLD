package LLD.TicTacToe;


import LLD.TicTacToe.Constants.Move;
import LLD.TicTacToe.Constants.Status;
import LLD.TicTacToe.Games.TicTacToe;

import java.util.Scanner;

import static LLD.TicTacToe.Constants.Status.*;

class Main {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        TicTacToe game = new TicTacToe(3, "Sahil", "Rishab");
        Status status = IN_PROGRESS;
        while(IN_PROGRESS.equals(status)) {
            if (game.player1Turn) {
                System.out.printf("It's %s's turn\n", game.player1);
            } else {
                System.out.printf("It's %s's turn\n", game.player2);
            }
            String move = scn.next().trim().toUpperCase();
            status = game.makeMove(Move.valueOf(move));
            if(INVALID_MOVE.equals(status)) {
                System.out.println("This is an invalid move. Try again.");
                status = IN_PROGRESS;
            }
        }
        String result = game.getResult(status);
        System.out.println(result);

    }
}