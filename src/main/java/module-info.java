module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens de.hdm_stuttgart.love_calculator.gui.GuiController to javafx.fxml;
    opens de.hdm_stuttgart.love_calculator.gui to javafx.fxml;

    exports de.hdm_stuttgart.love_calculator.gui;
    exports de.hdm_stuttgart.love_calculator.gui.GuiController;

}