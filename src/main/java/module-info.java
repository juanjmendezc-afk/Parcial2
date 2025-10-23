module co.edu.uniquindio.parcial2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.parcial2 to javafx.fxml;
    exports co.edu.uniquindio.parcial2;
    exports co.edu.uniquindio.parcial2.Decorator;
    opens co.edu.uniquindio.parcial2.Decorator to javafx.fxml;
    opens co.edu.uniquindio.parcial2.controller to javafx.fxml;
    exports co.edu.uniquindio.parcial2.controller;

}