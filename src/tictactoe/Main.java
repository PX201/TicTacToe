package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean restart = true;
        TicTacToe.gameExplanation();
        while (restart) {
            try {
                TicTacToe game = new TicTacToe();
                game.play();

                System.out.println("Do you want to play again? (y/n):");
                String input = scanner.next();
                if (input.equals("n")) {
                    restart = false;
                }
            } catch (Exception e) {
                restart = false;
            }
        }

        scanner.close();
    }
}
