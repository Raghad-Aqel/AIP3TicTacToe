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
    private int tie = 0;

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




    public void playAgain(ActionEvent event) {
        i = 0;
        roundCount++;

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = ' ';

        for (int i = 0; i < pane.getChildren().size(); i++) {
            ((Button) (pane.getChildren().get(i))).setText("");
        }

        pane.setDisable(false);

        if (roundCount <= 6) {
            roundLabel.setText("Round: " + roundCount);

            boolean isHumanStart = Math.random() < 0.5;

            if (isHumanStart) {
                roundLabel.setText("Round: " + roundCount + " (Human starts)");
            } else {
                roundLabel.setText("Round: " + roundCount + " (Computer starts)");

                Move move = MiniMax.findBestMove(board);
                board[move.row][move.col] = 'o';
                Button btn = chooseTheButton(move.col, move.row);
                btn.setText("o");
                i++;
            }
        }

        if (roundCount >= 6) {
            String winner = displayFinalResults();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(winner);
            alert.show();
            //pane.setDisable(true);

            roundCount = 1;
            humanWins = 0;
            computerWins = 0;
            tie =0;
            playerXLabel.setText("0");
            playerOLabel.setText("0");
            tieLabel.setText("0");
            roundLabel.setText("Round: 1");
        }
    }

    //old

//    public void playAgain(ActionEvent event) {
//        i = 0;
//        roundCount++;
//
//        //whoStarts();
//
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++)
//                board[i][j] = ' ';
//
//        for (int i = 0; i < pane.getChildren().size(); i++) {
//            ((Button) (pane.getChildren().get(i))).setText("");
//        }
//
//        pane.setDisable(false);
//
//        if (roundCount <= 6) {
//            roundLabel.setText("Round: " + roundCount);
//        }
//
//        if (roundCount >= 6) {
//            String winner = displayFinalResults();
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setContentText(winner);
//            alert.show();
//            pane.setDisable(true);
//
//            roundCount = 0;
//            humanWins = 0;
//            computerWins = 0;
//            tie =0;
//            playerXLabel.setText("0");
//            playerOLabel.setText("0");
//            tieLabel.setText("0");
//            roundLabel.setText("Round: 1");
//        }
//
//    }
//
    private String displayFinalResults() {
        String winner = "";

        int maxWins = Math.max(humanWins, Math.max(computerWins, tie));

        if (maxWins == humanWins) {
            winner = "Human Wins: " + humanWins + "\nComputer Wins: " + computerWins;
        } else if (maxWins == tie) {
            winner = "It's a Tie! \nHuman Wins: " + humanWins + "\nComputer Wins: " + computerWins+ "\nTie: " + tie;
        } else {
            winner = "The Computer is The Winner! \nComputer Wins: " + computerWins + "\nHuman Wins: " + humanWins + "\nTie: " + tie;
        }
        return winner;
    }

    private void action(int x, int y) {
        int flag = 0;
        Button btn = chooseTheButton(x, y);
        if (board[y][x] == ' ') {
            btn.setText("x");
            board[y][x] = 'x';
            i++;

            char result = checkWinner();
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
            btn = chooseTheButton(move.col, move.row);
            btn.setText("o");
            i++;

            result = checkWinner();
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
                tie++;
                break;
        }
    }
    private static int[] mapping(int n) {
        int[] array = {n%3, n/3};
        return array;
    }

    private Button chooseTheButton(int x, int y) {
        int[][] coordinates = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};

        for (int i = 0; i < coordinates.length; i++) {
            if (x == coordinates[i][1] && y == coordinates[i][0])
                return buttons[i];
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
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = ' ';
    }

    private static char checkWinner() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int i = 0; i < winningCombinations.length; i++) {
            int[] position1 = mapping(winningCombinations[i][0]);
            int[] position2  = mapping(winningCombinations[i][1]);
            int[] position3 = mapping(winningCombinations[i][2]);
            if (board[position1[0]][position1[1]] != ' ' && board[position1[0]][position1[1]] == board[position2 [0]][position2 [1]] && board[position2 [0]][position2 [1]] == board[position3[0]][position3[1]]) {
                return board[position1[0]][position1[1]];
            }
        }
        return ' ';
    }
}


//    public void playAgain1(ActionEvent event) {
//        i = 0;
//        roundCount++;
//
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++)
//                board[i][j] = ' ';
//
//        for (int i = 0; i < pane.getChildren().size(); i++) {
//            ((Button) (pane.getChildren().get(i))).setText("");
//        }
//
//        pane.setDisable(false);
//
//        if (roundCount <= 6) {
//            boolean isHumanStart = Math.random() < 0.5;
//
//            if (isHumanStart) {
//                roundLabel.setText("Round: " + roundCount + " (Human starts)");
//            } else {
//                roundLabel.setText("Round: " + roundCount + " (Computer starts)");
//
//                Move move = MiniMax.findBestMove(board);
//                board[move.row][move.col] = 'o';
//                Button btn = chooseTheButton(move.col, move.row);
//                btn.setText("o");
//                i++;
//
//                char result = checkWinner();
//                if (result != ' ') {
//                    updateResultLabel(result);
//                    pane.setDisable(true);
//
//                    if (roundCount < 6) {
//                        roundLabel.setText("Round: " + roundCount);
//                    }
//                    return;
//                }
//            }
//
//            if (i > 8) {
//                updateResultLabel('T');
//                pane.setDisable(true);
//
//                if (roundCount < 6) {
//                    roundLabel.setText("Round: " + roundCount);
//                }
//                return;
//            }
//        }
//
//        if (roundCount >= 6) {
//            String winner = displayFinalResults();
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setContentText(winner);
//            alert.show();
//
//            roundCount = 0;
//            humanWins = 0;
//            computerWins = 0;
//            tie = 0;
//            playerXLabel.setText("0");
//            playerOLabel.setText("0");
//            tieLabel.setText("0");
//            roundLabel.setText("Round: 1");
//        }
//    }
