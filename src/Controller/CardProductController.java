package Controller;

import Model.Product;
import Model.CustomerItem;
import Model.data;
import dao.interfaces.*;
import dao.impl.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Controller MAGRO - Só chama DAO, zero SQL direto MVC correto
 */
public class CardProductController implements Initializable {

    @FXML
    private AnchorPane prod_form;
    @FXML
    private ImageView prod_imageView;
    @FXML
    private Label prod_name, prod_price;
    @FXML
    private Spinner<Integer> prod_spinner;
    @FXML
    private Button prod_addBtn;

    private Product product;
    private IProductDAO productDAO = new ProductDAOImpl();
    private ICustomerDAO customerDAO = new CustomerDAOImpl();

    public void setData(Product product) {
        this.product = product;
        prod_name.setText(product.getProductName());
        prod_price.setText(product.getPrice() + " Mz");
        String path = "File:" + product.getImage();
        prod_imageView.setImage(new Image(path, 210, 117, false, true));
    }

    @FXML
    public void addBtn() {
        int qty = prod_spinner.getValue();
        if (qty == 0) {
            new Alert(Alert.AlertType.ERROR, "Selecione quantidade!").showAndWait();
            return;
        }
        try {
            Product pDB = productDAO.findByProdId(product.getProductId());
            if (pDB == null || pDB.getStock() < qty) {
                new Alert(Alert.AlertType.ERROR, "Sem estoque suficiente!").showAndWait();
                return;
            }
            if (!pDB.getStatus().equalsIgnoreCase("Disponivel")) {
                new Alert(Alert.AlertType.ERROR, "Produto indisponível!").showAndWait();
                return;
            }

            // Cria item do carrinho com username atual
            CustomerItem item = new CustomerItem(0, data.cID, product.getProductId(), product.getProductName(), product.getType(), qty, qty * product.getPrice(), null, product.getImage(), data.username);
            customerDAO.save(item);

            // Atualiza estoque via DAO
            int newStock = pDB.getStock() - qty;
            String newStatus = newStock == 0 ? "Indisponivel" : pDB.getStatus();
            productDAO.updateStock(product.getProductId(), newStock, newStatus);

            new Alert(Alert.AlertType.INFORMATION, "Adicionado com sucesso!").showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage()).showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prod_spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
    }
}
