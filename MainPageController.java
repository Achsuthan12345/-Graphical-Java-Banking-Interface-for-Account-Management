package project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class MainPageController implements Initializable {

    @FXML
    private Button button, button1; 
  
    private static Customer globalCustomer;
    
    public static void setGlobalCustomer(Customer customer) {
        globalCustomer = customer;
    }
    public static Customer getGlobalCustomer() {
        return globalCustomer;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void switchToManagerLogin(ActionEvent event) throws IOException {
        loadScene("ManagerLogin.fxml");
    }

    @FXML
    private void switchToCustomerLogin(ActionEvent event) throws IOException {
        loadScene("CustomerLogin.fxml");
    }
    
    private void loadScene(String fxmlFileName) throws IOException {
        ((Stage) button.getScene().getWindow()).close();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

}

