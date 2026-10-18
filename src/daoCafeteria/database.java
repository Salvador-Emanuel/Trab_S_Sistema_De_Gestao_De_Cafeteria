package daoCafeteria;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conexão Singleton simples
 */
public class database {

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/siscafe", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
