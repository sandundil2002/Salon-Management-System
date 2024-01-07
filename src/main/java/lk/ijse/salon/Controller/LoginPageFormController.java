package lk.ijse.salon.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.salon.bo.custom.EmployeesBO;
import lk.ijse.salon.bo.custom.impl.EmployeesBOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageFormController {
    public AnchorPane Signup;
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane loginForm;

    EmployeesBO employeesBO = new EmployeesBOImpl();

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, SQLException {
        try {
            boolean isOkey = employeesBO.loginEmployee(username.getText(),password.getText());
            if(isOkey) {
                Parent root = FXMLLoader.load(this.getClass().getResource("/View/mainForm.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) loginForm.getScene().getWindow();
                stage1.close();
            }
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/SignupForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginForm.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Signup form");
        stage.centerOnScreen();
    }
}

