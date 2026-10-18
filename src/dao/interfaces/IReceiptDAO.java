package dao.interfaces;

import Model.Receipt;

public interface IReceiptDAO extends IGenericDAO<Receipt> {

    double sumTotalByCustomerId(int cID) throws Exception;
}
