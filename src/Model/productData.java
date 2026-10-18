package Model;

import java.sql.Date;

/**
 * Classe legada mantida por compatibilidade com FXML antigos Herda de
 * BaseEntity agora
 */
public class productData extends BaseEntity {

    private String productId, productName, type, status, image;
    private int stock, quantity;
    private double price;

    public productData(int id, String productId, String productName, String type, int stock, double price, String status, String image, Date date) {
        super(id, date);
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public productData(int id, String productId, String productName, String type, int quantity, double price, String image, Date date) {
        super(id, date);
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    @Override
    public String getDisplayName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }
}
