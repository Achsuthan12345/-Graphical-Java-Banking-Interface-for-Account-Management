package project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CustomerLoginController {

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

    private File findCustomerFile(String username) {
        String filename = username + ".txt";
        File[] files = new File("src/project/BankAccounts").listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(filename)) {
                    return file;
                }
            }
        }
        return null;
    }

    private void handleLogin() throws FileNotFoundException, IOException {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
        File customerFile = findCustomerFile(enteredUsername);
        Scanner scanner = new Scanner(customerFile); 
        String savedUsername = scanner.nextLine();
        String savedPassword = scanner.nextLine();
        double balance = Double.parseDouble(scanner.nextLine());
        if (enteredPassword.equals(savedPassword)) {
            Customer loggedInCustomer = new Customer(savedUsername, savedPassword, balance);
            MainPageController.setGlobalCustomer(loggedInCustomer);
            loadScene("CustomerPage.fxml");
        } else {
            label.setText("Incorrect password or username");
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

