module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;

    opens de.hdm_stuttgart.love_calculator.Gui.GuiController to javafx.fxml;
    //opens de.hdm_stuttgart.love_calculator.gui.SettingsController to javafx.fxml;
    opens de.hdm_stuttgart.love_calculator.Gui to javafx.fxml;

    exports de.hdm_stuttgart.love_calculator.Gui;
    exports de.hdm_stuttgart.love_calculator.Gui.GuiController;
    exports de.hdm_stuttgart.love_calculator.Gui.GuiFactory;
    opens de.hdm_stuttgart.love_calculator.Gui.GuiFactory to javafx.fxml;
    exports de.hdm_stuttgart.love_calculator.Gui.GameModes;
    opens de.hdm_stuttgart.love_calculator.Gui.GameModes to javafx.fxml;

}