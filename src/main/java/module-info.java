module com.example.aip3tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.aip3tictactoe to javafx.fxml;
    exports com.example.aip3tictactoe;
}