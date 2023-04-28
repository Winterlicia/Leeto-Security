package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class View {
    @FXML
    public TextField dateLabel;
    @FXML
    public TextField keyLabel;
    @FXML
    public TextField passwordLabel;

    public static void setDateCreatedLabel(Password password, Label label) {
        label.setText("Date Created: "+password.getTimeCreated());
    }
    public static void setKeyCreatedLabel(Password password, Label label) {
        label.setText("Key: "+password.getKey());
    }
    public static void setPasswordCreatedLabel(Password password, Label label) {
        label.setText("Password: "+password.getName());
    }


}
