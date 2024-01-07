package lk.ijse.salon.bo.custom.impl;

import lk.ijse.salon.bo.custom.CustomersBO;
import lk.ijse.salon.dao.DAOFactory;
import lk.ijse.salon.dao.custom.CustomersDAO;
import lk.ijse.salon.dto.CustomerDto;
import lk.ijse.salon.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersBOImpl implements CustomersBO {
    CustomersDAO customersDAO = (CustomersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDto> loadAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customersDAO.loadAll();
        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers){
            customerDtos.add(new CustomerDto(
                    customer.getC_id(),
                    customer.getFirst_name(),
                    customer.getNic(),
                    customer.getAddress(),
                    customer.getPhone_number(),
                    customer.getGender()));
        }
        return customerDtos;
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customersDAO.save(new Customer(
                dto.getC_id(),
                dto.getFirst_name(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone_number(),
                dto.getGender()));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customersDAO.update(new Customer(
                dto.getC_id(),
                dto.getFirst_name(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone_number(),
                dto.getGender()));
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customersDAO.search(id);
        CustomerDto customerDto = new CustomerDto(
                customer.getC_id(),
                customer.getFirst_name(),
                customer.getNic(),
                customer.getAddress(),
                customer.getPhone_number(),
                customer.getGender());
        return customerDto;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customersDAO.delete(id);
    }

    @Override
    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        return customersDAO.generateId();
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return customersDAO.getCustomerCount();
    }
}
