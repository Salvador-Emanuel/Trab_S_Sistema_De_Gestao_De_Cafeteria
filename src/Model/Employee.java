package Model;

/**
 * Employee herda de Person - Demonstra HERANÇA
 */
public class Employee extends Person {

    private int id;
    private String password;

    public Employee(String username, String role) {
        super(username, role);
    }

    public Employee(int id, String username, String role) {
        super(username, role);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
