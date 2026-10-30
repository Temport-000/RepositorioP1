module co.edu.uniquindio.poo.empresajfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.taller to javafx.fxml;
    exports co.edu.uniquindio.poo.taller;
    exports co.edu.uniquindio.poo.taller.viewController;
    opens co.edu.uniquindio.poo.taller.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.taller.model to javafx.base;

}
