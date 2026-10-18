package Model;

import java.sql.Date;

/**
 * Product herda de BaseEntity - era seu productData Representa produto do
 * inventário
 */
public class Product extends BaseEntity {

    private String productId;
    private String productName;
    private String type;
    private int stock;
    private double price;
    private String status;
    private String image;
    private String ownerUsername; // quem criou - filtro por usuário

    public Product(int id, String productId, String productName, String type, int stock, double price, String status, String image, Date date, String ownerUsername) {
        super(id, date);
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.ownerUsername = ownerUsername;
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

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
