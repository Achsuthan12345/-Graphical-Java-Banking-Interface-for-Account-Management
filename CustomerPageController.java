package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomerPageController implements Initializable {

    @FXML
    private Button buttonWithdrawOrDeposit;
    @FXML
    private Button buttonLogout;
    @FXML
    private Button buttonBack;
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleWithdrawOrDeposit() throws IOException {
        loadScene("WithdrawDepositBalancePurchase.fxml");
    }

    @FXML
    private void handleLogout() throws IOException {
        loadScene("MainPage.fxml");
    }

    @FXML
    private void handleBack() throws IOException {
        loadScene("CustomerLogin.fxml");
    }

    private void loadScene(String fxmlFileName) throws IOException {
        ((Stage) buttonBack.getScene().getWindow()).close();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }
    
}