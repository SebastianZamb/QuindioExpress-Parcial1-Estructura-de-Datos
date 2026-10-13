module co.edu.uniquindio.quindioexpress {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.quindioexpress to javafx.fxml;
    exports co.edu.uniquindio.quindioexpress;
}