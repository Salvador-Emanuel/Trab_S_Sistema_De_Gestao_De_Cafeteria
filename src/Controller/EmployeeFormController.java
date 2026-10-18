package Controller;

import Model.*;
import dao.interfaces.*;
import dao.impl.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de Employee - Versão MVC magra Demonstra uso de DAO + filtro por
 * username
 */
public class EmployeeFormController implements Initializable {

    // DAOs - Injeção via Interface (Polimorfismo)
    private IProductDAO productDAO = new ProductDAOImpl();
    private IReceiptDAO receiptDAO = new ReceiptDAOImpl();
    private ICustomerDAO customerDAO = new CustomerDAOImpl();

    /**
     * LISTA SÓ PRODUTOS DO USUÁRIO ATUAL - requisito principal
     */
    public ObservableList<Product> inventoryDataList() {
        try {
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

    /**
     * LISTA SÓ VENDAS DO USUÁRIO ATUAL
     */
    public ObservableList<Receipt> customersDataList() {
        try {
            return FXCollections.observableArrayList(receiptDAO.findByUsername(data.username));
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public ObservableList<CustomerItem> menuGetOrder() {
        try {
            return FXCollections.observableArrayList(customerDAO.findByCustomerId(data.cID));
        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
