package Model;

import java.sql.Date;

/**
 * Receipt herda de BaseEntity - era customersData Representa venda finalizada
 */
public class Receipt extends BaseEntity {

    private int customer_id;
    private double total;
    private String em_username;

    public Receipt(int id, int customer_id, double total, Date date, String em_username) {
        super(id, date);
        this.customer_id = customer_id;
        this.total = total;
        this.em_username = em_username;
    }

    @Override
    public String getDisplayName() {
        return String.valueOf(customer_id);
    }

    public int getCustomerID() {
        return customer_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public double getTotal() {
        return total;
    }

    public String getEmUsername() {
        return em_username;
    }

    public String getEm_username() {
        return em_username;
    }
}
