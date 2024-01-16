package com.example.aip3tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class TicTacToeController implements Initializable{
    public static char[][] board = new char[3][3];
    int i = 0;


    private int roundCount = 1;
    private int humanWins = 0;
    private int computerWins = 0;

    private int Tie = 0;

    @FXML
    private Label playerXLabel;
    @FXML
    private Label playerOLabel;
    @FXML
    private Label tieLabel;
    @FXML
    private Label roundLabel;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private AnchorPane pane;

    @FXML
    void button1OnClick(ActionEvent event) {
        action(0, 0);
    }

    @FXML
    void button2OnClick(ActionEvent event) {
        action(1, 0);
    }

    @FXML
    void button3OnClick(ActionEvent event) {
        action(2, 0);
    }

    @FXML
    void button4OnClick(ActionEvent event) {
        action(0, 1);
    }

    @FXML
    void button5OnClick(ActionEvent event) {
        action(1, 1);
    }

    @FXML
    void button6OnClick(ActionEvent event) {
        action(2, 1);
    }

    @FXML
    void button7OnClick(ActionEvent event) {
        action(0, 2);
    }

    @FXML
    void button8OnClick(ActionEvent event) {
        action(1, 2);
    }

    @FXML
    void button9OnClick(ActionEvent event) {
        action(2, 2);
    }

    @FXML
    void backButtonOnClick(ActionEvent event) throws IOException {
        showStage("Hello-view.fxml");
    }




//    public void playAgain(ActionEvent event) {
//        i = 0;
//        roundCount++;
//
//        // Reset the board
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++)
//                board[i][j] = ' ';
//
//        // Clear buttons
//        for (int i = 0; i < pane.getChildren().size(); i++) {
//            ((Button) (pane.getChildren().get(i))).setText("");
//        }
//
//        pane.setDisable(false);
//
//        if (roundCount <= 6) {
//            RoundLabel.setText("Round: " + roundCount);
//
//            // Randomly determine the starting player
//            boolean isHumanStart = Math.random() < 0.5;
//
//            if (isHumanStart) {
//                // Human starts
//                RoundLabel.setText("Round: " + roundCount + " (Human starts)");
//            } else {
//                // Computer starts
//                RoundLabel.setText("Round: " + roundCount + " (Computer starts)");
//
//                // Computer's first move using Minimax
//                Move move = MiniMax.findBestMove(board);
//                board[move.row][move.col] = 'o';
//                Button btn = selectButton(move.col, move.row);
//                btn.setText("o");
//                i++;
//            }
//
//            // Check for a tie after both moves
//            if (i > 8) {
//                updateResultLabel('T');
//                pane.setDisable(true);
//
//                if (roundCount < 6) {
//                    RoundLabel.setText("Round: " + roundCount);
//                }
//                return;
//            }
//        }
//
//        if (roundCount == 6) {
//            // Display the final results after the 5th round
//            String winner = displayFinalResults();
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setContentText(winner);
//            alert.show();
//            pane.setDisable(true);
//
//            // Reset counts and labels for a new game
//            roundCount = 0;
//            humanWins = 0;
//            computerWins = 0;
//            Tie = 0;
//            playerX.setText("0");
//            playerO.setText("0");
//            playerT.setText("0");
//            RoundLabel.setText("Round: 1");
//        }
//    }




    public void playAgain(ActionEvent event) {
        i = 0;
        roundCount++;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';

        for (int i = 0; i < pane.getChildren().size(); i++) {
            ((Button) (pane.getChildren().get(i))).setText("");
        }

        pane.setDisable(false);

        if (roundCount <= 6) {
            roundLabel.setText("Round: " + roundCount);
        }

        if (roundCount == 6) {
            String winner = displayFinalResults();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(winner);
            alert.show();
            pane.setDisable(true);

            roundCount = 0;
            humanWins = 0;
            computerWins = 0;
            Tie=0;
            playerXLabel.setText("0");
            playerOLabel.setText("0");
            tieLabel.setText("0");
            roundLabel.setText("Round: 1");
        }

    }

    private String displayFinalResults() {
        String winner = "";

        int maxWins = Math.max(humanWins, Math.max(computerWins, Tie));

        if (maxWins == humanWins) {
            winner = "Human Wins: " + humanWins + "\nComputer Wins: " + computerWins;
        } else if (maxWins == Tie) {
            winner = "It's a Tie! \nHuman Wins: " + humanWins + "\nComputer Wins: " + computerWins+ "\nTie: " + Tie;
        } else {
            winner = "The Computer is The Winner! \nComputer Wins: " + computerWins + "\nHuman Wins: " + humanWins + "\nTie: " + Tie;
        }
        return winner;
    }


    private void action(int x, int y) {
        int flag = 0;
        Button btn = selectButton(x, y);
        if (board[y][x] == ' ') {
            btn.setText("x");
            board[y][x] = 'x';
            i++;

            char result = evaluation();
            if (result != ' ') {
                flag = 1;
                updateResultLabel(result);
                pane.setDisable(true);

                if (roundCount < 6) {
                    roundLabel.setText("Round: " + (roundCount ));
                }
                return;
            }

            if (i > 8) {
                if (flag == 0) {
                    updateResultLabel('T');
                    pane.setDisable(true);

                    if (roundCount < 6) {
                        roundLabel.setText("Round: " + (roundCount ));
                    }
                    return;
                }
            }

            Move move = MiniMax.findBestMove(board);
            board[move.row][move.col] = 'o';
            btn = selectButton(move.col, move.row);
            btn.setText("o");
            i++;

            result = evaluation();
            if (result != ' ') {
                flag = 1;
                updateResultLabel(result);
                pane.setDisable(true);

                if (roundCount < 6) {
                    roundLabel.setText("Round: " + (roundCount ));
                }
                return;
            }
        }

    }

    private void updateResultLabel(char result) {
        switch (result) {
            case 'x':
                playerXLabel.setText(String.valueOf(Integer.parseInt(playerXLabel.getText()) + 1));
                humanWins++;
                break;
            case 'o':
                playerOLabel.setText(String.valueOf(Integer.parseInt(playerOLabel.getText()) + 1));
                computerWins++;
                break;
            case 'T':
                tieLabel.setText(String.valueOf(Integer.parseInt(tieLabel.getText()) + 1));
                Tie++;
                break;
        }
    }
    private static int[] mapping(int n) {
        int x = n%3;
        int y = n/3;

        int[] arr = {x, y};
        return arr;
    }


    private Button selectButton(int x, int y) {
        int[][] coo = {{0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}};
        Button[] btn = {button1, button2, button3, button4, button5, button6, button7, button8, button9};


        for (int i = 0; i < coo.length; i++) {
            if (x == coo[i][1] && y == coo[i][0])
                return btn[i];
        }

        return null;
    }


    private void showStage(String fileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fileName));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.centerOnScreen();
        Main.stage.show();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    private static char evaluation() {
        int[][] win = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };


        for (int i = 0; i < win.length; i++) {
            int[] n1 = mapping(win[i][0]);
            int[] n2 = mapping(win[i][1]);
            int[] n3 = mapping(win[i][2]);
            if (board[n1[0]][n1[1]] != ' ' && board[n1[0]][n1[1]] == board[n2[0]][n2[1]] && board[n2[0]][n2[1]] == board[n3[0]][n3[1]]) {
                return board[n1[0]][n1[1]];
            }
        }

        return ' ';
    }
}