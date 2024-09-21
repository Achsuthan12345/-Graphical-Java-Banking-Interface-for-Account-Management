package project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WithdrawDepositBalancePurchaseController {

    private Customer loggedInCustomer;
    @FXML
    private Button withdraw, deposit, purchase1, purchase2, getBalance, back, logout;
    @FXML
    private TextField withdrawText, depositText, bid1, bid2;
    @FXML
    private Label withdrawLabel, purchase, depositLabel, balance, role;

    
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        loadScene("MainPage.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        loadScene("CustomerPage.fxml");
    }
    
    @FXML
    private void handleWithdraw(ActionEvent event) throws IOException {
        Customer loggedInCustomer = MainPageController.getGlobalCustomer();
        double amount = Double.parseDouble(withdrawText.getText());
        if (amount < 0) {
            withdrawLabel.setText("Error");
        } else {
            if (loggedInCustomer.withdraw(amount)) {
                withdrawLabel.setText("Withdraw Successful");
            } else {
                withdrawLabel.setText("Insufficient balance");
            }
            loggedInCustomer.saveToFile();
        }
    }

    

    @FXML
    private void handleDeposit(ActionEvent event) throws IOException {
        Customer loggedInCustomer = MainPageController.getGlobalCustomer();
        double amount = Double.parseDouble(depositText.getText());
        if (amount < 0) {
            depositLabel.setText("Error");
        } else {
            loggedInCustomer.deposit(amount);
            depositLabel.setText("Deposit Successful");
            loggedInCustomer.saveToFile();
        }
    }

    @FXML
    private void handleGetBalance(ActionEvent event) {
        Customer loggedInCustomer = MainPageController.getGlobalCustomer();
        balance.setText(String.valueOf(loggedInCustomer.getBalance()));
        role.setText(loggedInCustomer.getLevel());
    }
    
    @FXML
    private void handlePurchase1(ActionEvent event) throws IOException {
        handlePurchase(bid1.getText());
    }

    @FXML
    private void handlePurchase2(ActionEvent event) throws IOException {
        handlePurchase(bid2.getText());
    }

    private void handlePurchase(String bidAmountText) throws IOException {
        double bidAmount = Double.parseDouble(bidAmountText);
        if (bidAmount < 50) {
            purchase.setText("Bid amount must be at least $50");
            return;
        }
        Customer loggedInCustomer = MainPageController.getGlobalCustomer();
        double fee = loggedInCustomer.getCalculateFee();
        double totalAmount = bidAmount + fee;
        if (loggedInCustomer.withdraw(totalAmount)) {
            purchase.setText("Purchase Successful");
        } else {
            purchase.setText("Insufficient balance!");
        }   
        loggedInCustomer.saveToFile();
    }

    private void loadScene(String fxmlFileName) throws IOException {
        ((Stage) back.getScene().getWindow()).close();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}


