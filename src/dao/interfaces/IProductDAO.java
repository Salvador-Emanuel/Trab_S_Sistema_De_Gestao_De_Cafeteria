package dao.interfaces;

import Model.Product;

public interface IProductDAO extends IGenericDAO<Product> {

    Product findByProdId(String prodId) throws Exception;

    boolean updateStock(String prodId, int newStock, String status) throws Exception;

    boolean existsProdId(String prodId) throws Exception;
}
