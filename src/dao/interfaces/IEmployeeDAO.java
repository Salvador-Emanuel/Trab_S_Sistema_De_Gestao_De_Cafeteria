package dao.interfaces;

import Model.Employee;

public interface IEmployeeDAO extends IGenericDAO<Employee> {

    /**
     * Autentica usuário
     */
    Employee authenticate(String username, String password) throws Exception;

    boolean existsUsername(String username) throws Exception;
}
