package Controller;

import Model.Employee;
import Model.data;
import dao.interfaces.IEmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Login Controller - Usa EmployeeDAO para autenticar
 */
public class FXMLDocumentController {

    @FXML
    private TextField si_username;
    @FXML
    private PasswordField si_password;
    @FXML
    private Button si_loginBtn;

    private IEmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @FXML
    public void loginBtn() {
        if (si_username.getText().trim().isEmpty() || si_password.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Preencha todos os campos!").showAndWait();
            return;
        }
        try {
            Employee emp = employeeDAO.authenticate(si_username.getText().trim(), si_password.getText());
            if (emp != null) {
                data.username = emp.getUsername();
                data.role = emp.getRole();
                String fxmlPath = emp.getRole().equalsIgnoreCase("admin") ? "/View/MainForm.fxml" : "/View/Employee.fxml";
                Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SISCAFÉ");
                stage.show();
                ((Stage) si_loginBtn.getScene().getWindow()).close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Usuário ou senha incorretos!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
        }
    }
}
