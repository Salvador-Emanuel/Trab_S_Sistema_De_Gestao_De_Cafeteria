package dao.impl;

import Model.Product;
import dao.interfaces.IProductDAO;
import daoCafeteria.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de Produto - Implementa IProductDAO Todo SQL isolado aqui - MVC correto
 */
public class ProductDAOImpl implements IProductDAO {

    @Override
    public List<Product> findByUsername(String username) throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE username = ?";
        try (Connection conn = database.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {
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
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = database.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public Product findByProdId(String prodId) throws Exception {
        String sql = "SELECT * FROM product WHERE prod_id = ?";
        try (Connection conn = database.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prodId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateStock(String prodId, int newStock, String status) throws Exception {
        String sql = "UPDATE product SET stock = ?, status = ? WHERE prod_id = ?";
        try (Connection conn = database.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newStock);
            ps.setString(2, status);
            ps.setString(3, prodId);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean existsProdId(String prodId) throws Exception {
        String sql = "SELECT prod_id FROM product WHERE prod_id = ?";
        try (Connection conn = database.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prodId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private Product map(ResultSet rs) throws Exception {
        return new Product(
                rs.getInt("id"),
                rs.getString("prod_id"),
                rs.getString("prod_name"),
                rs.getString("type"),
                rs.getInt("stock"),
                rs.getDouble("price"),
                rs.getString("status"),
                rs.getString("image"),
                rs.getDate("date"),
                rs.getString("username")
        );
    }

    @Override
    public Product findById(int id) throws Exception {
        String sql = "SELECT * FROM product WHERE id=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    @Override
    public boolean save(Product p) throws Exception {
        String sql = "INSERT INTO product(prod_id,prod_name,type,stock,price,status,image,date,username) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getProductId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getType());
            ps.setInt(4, p.getStock());
            ps.setDouble(5, p.getPrice());
            ps.setString(6, p.getStatus());
            ps.setString(7, p.getImage());
            ps.setDate(8, p.getDate());
            ps.setString(9, p.getOwnerUsername());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Product p) throws Exception {
        String sql = "UPDATE product SET prod_name=?,type=?,stock=?,price=?,status=?,image=?,date=? WHERE prod_id=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getType());
            ps.setInt(3, p.getStock());
            ps.setDouble(4, p.getPrice());
            ps.setString(5, p.getStatus());
            ps.setString(6, p.getImage());
            ps.setDate(7, p.getDate());
            ps.setString(8, p.getProductId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM product WHERE id=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
