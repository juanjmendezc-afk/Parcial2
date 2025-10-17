module co.edu.uniquindio.parcial2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.parcial2 to javafx.fxml;
    exports co.edu.uniquindio.parcial2;
}