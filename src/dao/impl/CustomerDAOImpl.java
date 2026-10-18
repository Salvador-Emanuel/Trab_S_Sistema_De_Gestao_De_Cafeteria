package dao.impl;

import Model.CustomerItem;
import dao.interfaces.ICustomerDAO;
import daoCafeteria.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {

    @Override
    public List<CustomerItem> findByUsername(String username) throws Exception {
        List<CustomerItem> list = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE em_username=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    @Override
    public List<CustomerItem> findByCustomerId(int cID) throws Exception {
        List<CustomerItem> list = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE customer_id=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, cID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    @Override
    public double sumPriceByCustomerId(int cID) throws Exception {
        String sql = "SELECT SUM(price) FROM customer WHERE customer_id=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, cID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        }
        return 0;
    }

    private CustomerItem map(ResultSet rs) throws Exception {
        return new CustomerItem(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("prod_id"), rs.getString("prod_name"), rs.getString("type"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date"), rs.getString("image"), rs.getString("em_username"));
    }

    @Override
    public List<CustomerItem> findAll() throws Exception {
        return new ArrayList<>();
    }

    @Override
    public CustomerItem findById(int id) throws Exception {
        return null;
    }

    @Override
    public boolean save(CustomerItem ci) throws Exception {
        String sql = "INSERT INTO customer(customer_id,prod_id,prod_name,type,quantity,price,date,image,em_username) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, ci.getCustomer_id());
            ps.setString(2, ci.getProd_id());
            ps.setString(3, ci.getProd_name());
            ps.setString(4, ci.getType());
            ps.setInt(5, ci.getQuantity());
            ps.setDouble(6, ci.getPrice());
            ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(8, ci.getImage());
            ps.setString(9, ci.getEm_username());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(CustomerItem e) throws Exception {
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM customer WHERE id=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
