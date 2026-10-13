module co.edu.uniquindio.quindioexpress {
    requires javafx.controls;
    requires javafx.fxml;
    requires co.edu.uniquindio.quindioexpress;


    opens co.edu.uniquindio.quindioexpress to javafx.fxml;
    opens co.edu.uniquindio.quindioexpress.ViewController to javafx.fxml;
    exports co.edu.uniquindio.quindioexpress;
    exports co.edu.uniquindio.quindioexpress.Comparators;
    opens co.edu.uniquindio.quindioexpress.Comparators to javafx.fxml;
}