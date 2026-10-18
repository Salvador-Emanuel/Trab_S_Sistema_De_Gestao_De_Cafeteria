package Model;

import java.sql.Date;

/**
 * CustomerItem herda de BaseEntity - era customer (carrinho)
 */
public class CustomerItem extends BaseEntity {

    private int customer_id;
    private String prod_id;
    private String prod_name;
    private String type;
    private int quantity;
    private double price;
    private String image;
    private String em_username;

    public CustomerItem(int id, int customer_id, String prod_id, String prod_name, String type, int quantity, double price, Date date, String image, String em_username) {
        super(id, date);
        this.customer_id = customer_id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.em_username = em_username;
    }

    @Override
    public String getDisplayName() {
        return prod_name;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getProd_id() {
        return prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getEm_username() {
        return em_username;
    }

    // compatibilidade com PropertyValueFactory antigo
    public String getProductName() {
        return prod_name;
    }

    public String getProductId() {
        return prod_id;
    }
}
