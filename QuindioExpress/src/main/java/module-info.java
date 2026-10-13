module co.edu.uniquindio.quindioexpress {
    requires javafx.controls;
    requires javafx.fxml;



    opens co.edu.uniquindio.quindioexpress to javafx.fxml;
    exports co.edu.uniquindio.quindioexpress;
    exports co.edu.uniquindio.quindioexpress.Comparators;
    opens co.edu.uniquindio.quindioexpress.Comparators to javafx.fxml;
}