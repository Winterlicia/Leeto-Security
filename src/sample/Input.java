package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.awt.event.ActionEvent;

public class Input {
    @FXML
    static PasswordField organizationField;
    @FXML
    static PasswordField OptionalKeyField;
    @FXML
    static Button CreatePasswordButton;
    @FXML
    PasswordField PasswordCheckField = new PasswordField();

    public void create_inputOrganization(ActionEvent event) {
        String input = organizationField.getText();
    }

    public void create_inputOptionalKey(ActionEvent event) {
        String input = OptionalKeyField.getText();
    }

    @FXML
    public void CreatePasswordButtonClick() {

        //Need some code here that will check if the organizationField has an input yet:
        if (organizationField.getText() != null) {

            CreatePasswordButton.setOnAction(actionEvent -> {
                //On button click create some sample password with Password Creation Algorithm:

                String generatedPassword = Algorithms.createPassword(5);
                String time;
                try {
                    time = Algorithms.getDate_Time();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //Might need to create a 'key' randomizer/creator, but I'm still not sure what the 'key' is.
                String key = "";
                try {
                    passwordPrintingTesting.storePassword();
                    passwordPrintingTesting.appendFile(generatedPassword, key, time);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        } else {
            //Change this later to affect the actual app itself, not the system
            System.out.println("Required: Enter Organization Name");
        }
    }

    @FXML
    public String rate(String password) {
        try {
            return Algorithms.ratePassword(password);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
