package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

/**
 *
 * @author clee2
 */
public class Controller implements Initializable {
    @FXML
    private ListView<Password> listView;
    @FXML
    public Label dateLabel;
    @FXML
    public Label keyLabel;
    @FXML
    public Label passwordLabel;

    @FXML
    TextField OrganizationField;
    @FXML
    TextField OptionalKeyField;
    @FXML
    Button CreatePasswordButton;

    @FXML
    Button PasswordCheckButton = new Button();
    @FXML
    Label ratingLabel = new Label();
    @FXML
    PasswordField PasswordCheckField;

    Password currentPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Update password list and add the password info with fx:id labels
            //listView.getItems().addAll(createPasswordList());
            ObservableList<Password> passwords = createPasswordList();
            listView.getItems().addAll(passwords);

            //Display only the name of the Password on the ListView:
            listView.setCellFactory(new Callback<ListView<Password>, ListCell<Password>>() {
                @Override
                public ListCell<Password> call(ListView<Password> listView) {
                    return new ListCell<Password>() {
                        @Override
                        protected void updateItem(Password item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getName());
                            }
                        }
                    };
                }
            });

            //Code to basically change the view according to the password that is selected:
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Password>() {
                @Override
                public void changed(ObservableValue<? extends Password> observableValue, Password password, Password t1) {

                    currentPassword = listView.getSelectionModel().getSelectedItem();
                    dateLabel.setText("Date Created: " + currentPassword.getTimeCreated());
                    keyLabel.setText("Key: " + currentPassword.getKey());
                    passwordLabel.setText("Password: " + currentPassword.getName());

                }

            });


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public ObservableList<Password> createPasswordList() throws Exception {
        FileReader fileReader = new FileReader("passwordlist.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        ObservableList<Password> passwords = FXCollections.observableArrayList();

        String line;
        Password data;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("/");
            data = new Password(parts[0], parts[1], parts[2]);
            passwords.add(data);
        }

        return passwords;

    }

    @FXML
    public void CreatePasswordButtonClick(ActionEvent actionEvent) {
        //On button click create some sample password with Password Creation Algorithm:
        String generatedPassword = Algorithms.createPassword(6);
        String time = "";
        String key = "";
        try {
            if (OrganizationField.getText().isEmpty()) {
                OrganizationField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        if (newValue.isEmpty()) {
                            OrganizationField.setText("Organization is Required");
                        }
                     }
                });
            } else {
                time = Algorithms.getDate_Time("");
                if (!OptionalKeyField.getText().isEmpty()) {
                    key = OptionalKeyField.getText();
                } else {
                    key = " ";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Might need to create a 'key' randomizer/creator, but I'm still not sure what the 'key' is.
        try {
            passwordPrintingTesting.storePassword();
            passwordPrintingTesting.appendFile(generatedPassword, key, time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Functionality Buttons
    public void PasswordCheckButton(ActionEvent actionEvent) {
        String password = PasswordCheckField.getText();
        System.out.println(Algorithms.ratePassword(password));
        ratingLabel.setText(Algorithms.ratePassword(password));
    }

}
