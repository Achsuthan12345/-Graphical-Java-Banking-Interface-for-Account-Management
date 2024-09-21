package project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import java.io.File;
import java.util.ArrayList;

public class ManagerPageController implements Initializable {

    @FXML
    private TableView<String> customerTableView;
    @FXML
    private TableColumn<String, String> customerDatabase;
    @FXML
    private Button addCustomerButton, back, logout, deleteCustomerButton;
    @FXML
    private Label label;
    @FXML
    private TextField usernameTextField, passwordTextField;

    private ArrayList<String> usernames = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllUsernames();
        customerDatabase.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue())); 
        customerTableView.getItems().addAll(usernames);
    }

    @FXML
    private void handleAddCustomer(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            label.setText("Username or password cannot be empty.");
            return; 
        }
        if (usernames.contains(username)) {
            label.setText("Username already exists.");
            return; 
        }
        customerTableView.getItems().add(username);
        usernames.add(username);
        usernameTextField.clear();
        passwordTextField.clear();
        saveCustomerToFile(username, password);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        ((Stage) back.getScene().getWindow()).close();
        loadScene("ManagerLogin.fxml");
    }

    @FXML
    private void homeScreen(ActionEvent event) throws IOException {
        ((Stage) logout.getScene().getWindow()).close();
        loadScene("MainPage.fxml");
    }
    
    @FXML
    private void handleDeleteCustomer(ActionEvent event) {
        int selectedIndex = customerTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String username = customerTableView.getItems().get(selectedIndex);
            customerTableView.getItems().remove(selectedIndex);
            deleteCustomerFile(username);
        }
    }
    
    private void loadAllUsernames() {
        File[] files = new File("src/project/BankAccounts").listFiles();
        if (files != null) {
            for (File file : files) {
                String filename = file.getName();
                String username = filename.substring(0, filename.lastIndexOf('.'));
                usernames.add(username);
            }
        }
    }

    private void saveCustomerToFile(String username, String password) throws IOException {
        String filename = username + ".txt";
        File file = new File("src/project/BankAccounts", filename); 
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(username);
        writer.newLine();
        writer.write(password);
        writer.newLine();
        writer.write("100");
        writer.newLine();
        writer.write("Role: Customer");
        writer.close();
    }

    private void deleteCustomerFile(String username) {
        String filename = "src/project/BankAccounts/" + username + ".txt";
        File fileToDelete = new File(filename);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
            usernames.remove(username);
        }
    }

    private void loadScene(String fxmlFileName) throws IOException {
        ((Stage) back.getScene().getWindow()).close();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}
