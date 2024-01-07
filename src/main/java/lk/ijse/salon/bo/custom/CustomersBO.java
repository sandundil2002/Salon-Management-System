package lk.ijse.salon.bo.custom;

import lk.ijse.salon.bo.SuperBO;
import lk.ijse.salon.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomersBO extends SuperBO {
    List<CustomerDto> loadAllCustomers() throws SQLException,ClassNotFoundException;

    boolean saveCustomer(CustomerDto dto) throws SQLException,ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException,ClassNotFoundException;

    CustomerDto searchCustomer(String id) throws SQLException,ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException,ClassNotFoundException;

    String generateCustomerId() throws SQLException,ClassNotFoundException;

    int getCustomerCount() throws SQLException,ClassNotFoundException;
}
