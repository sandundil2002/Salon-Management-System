package lk.ijse.salon.dao.custom;

import lk.ijse.salon.entity.Customer;

import java.sql.SQLException;

public interface CustomersDAO extends CrudDAO<Customer> {
    int getCustomerCount() throws SQLException,ClassNotFoundException;
}
