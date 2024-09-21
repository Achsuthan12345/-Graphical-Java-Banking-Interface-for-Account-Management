package project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ManagerLoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button button, back;
    @FXML
    private Label label;

    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        handleLogin();
    }
    
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        ((Stage) back.getScene().getWindow()).close();
        loadScene("MainPage.fxml");
    }

    private void handleLogin() throws IOException {
        if (username.getText().equals("admin") && password.getText().equals("admin")) {
            loadScene("ManagerPage.fxml");
        } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
            label.setText("Enter your data");
        } else {
            label.setText("Wrong Username or password");
        }
    }

    private void loadScene(String fxmlFileName) throws IOException {
        ((Stage) button.getScene().getWindow()).close();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}


