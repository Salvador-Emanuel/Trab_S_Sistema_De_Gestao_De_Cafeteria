package dao.interfaces;

import Model.CustomerItem;
import java.util.List;

public interface ICustomerDAO extends IGenericDAO<CustomerItem> {

    List<CustomerItem> findByCustomerId(int cID) throws Exception;

    double sumPriceByCustomerId(int cID) throws Exception;
}
