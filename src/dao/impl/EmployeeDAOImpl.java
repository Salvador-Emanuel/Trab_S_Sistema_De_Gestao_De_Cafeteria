package dao.impl;

import Model.Employee;
import dao.interfaces.IEmployeeDAO;
import daoCafeteria.database;
import java.sql.*;
import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {

    @Override
    public Employee authenticate(String username, String password) throws Exception {
        String sql = "SELECT username, role FROM employee WHERE username=? AND password=?";
        try (Connection conn = database.connectDB(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(rs.getString("username"), rs.getString("role"));
                }
            }
        }
        return null;
    }

    @Override
    public boolean existsUsername(String username) throws Exception {
        String sql = "SELECT username FROM employee WHERE username=?";
        try (Connection c = database.connectDB(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public List<Employee> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Employee> findByUsername(String u) throws Exception {
        return null;
    }

    @Override
    public Employee findById(int id) throws Exception {
        return null;
    }

    @Override
    public boolean save(Employee e) throws Exception {
        return false;
    }

    @Override
    public boolean update(Employee e) throws Exception {
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }
}
