package dao.interfaces;

import java.util.List;

/**
 * Interface Genérica - Demonstra INTERFACE e POLIMORFISMO
 *
 * @param <T> Tipo da entidade
 */
public interface IGenericDAO<T> {

    List<T> findAll() throws Exception;

    /**
     * Método principal do requisito: só dados do usuário atual
     */
    List<T> findByUsername(String username) throws Exception;

    T findById(int id) throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(int id) throws Exception;
}
