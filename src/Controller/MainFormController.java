package Controller;

import Model.*;
import dao.interfaces.*;
import dao.impl.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainForm para Admin - MVC com DAO Admin vê tudo, Employee vê só o seu (regra
 * no DAO)
 */
public class MainFormController implements Initializable {

    private IProductDAO productDAO = new ProductDAOImpl();
    private IReceiptDAO receiptDAO = new ReceiptDAOImpl();

    public ObservableList<Product> inventoryDataList() {
        try {
            // Admin vê tudo, Employee filtra via DAO
            if (data.role != null && data.role.equalsIgnoreCase("admin")) {
                return FXCollections.observableArrayList(productDAO.findAll());
            } else {
                return FXCollections.observableArrayList(productDAO.findByUsername(data.username));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<Receipt> customersDataList() {
        try {
            if (data.role != null && data.role.equalsIgnoreCase("admin")) {
                return FXCollections.observableArrayList(receiptDAO.findAll());
            } else {
                return FXCollections.observableArrayList(receiptDAO.findByUsername(data.username));
            }
        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
