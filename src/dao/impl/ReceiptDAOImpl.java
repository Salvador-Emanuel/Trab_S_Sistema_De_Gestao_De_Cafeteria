package dao.impl;

import Model.Receipt;
import dao.interfaces.IReceiptDAO;
import daoCafeteria.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements IReceiptDAO {

    @Override
    public List<Receipt> findByUsername(String username) throws Exception {
        List<Receipt> list = new ArrayList<>();
        String sql = "SELECT * FROM receipt WHERE em_username = ?";
        try (Connection conn = database.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Receipt(rs.getInt("id"), rs.getInt("customer_id"), rs.getDouble("total"), rs.getDate("date"), rs.getString("em_username")));
                }
            }
        }
        return list;
    }

    @Override
    public List<Receipt> findAll() throws Exception {
        List<Receipt> list = new ArrayList<>();
        String sql = "SELECT * FROM receipt";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Receipt(rs.getInt("id"), rs.getInt("customer_id"), rs.getDouble("total"), rs.getDate("date"), rs.getString("em_username")));
            }
        }
        return list;
    }

    @Override
    public boolean save(Receipt r) throws Exception {
        String sql = "INSERT INTO receipt(customer_id,total,date,em_username) VALUES(?,?,?,?)";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, r.getCustomerID());
            ps.setDouble(2, r.getTotal());
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(4, r.getEmUsername());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public double sumTotalByCustomerId(int cID) throws Exception {
        return 0;
    }

    @Override
    public Receipt findById(int id) throws Exception {
        return null;
    }

    @Override
    public boolean update(Receipt e) throws Exception {
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }
}
