package lk.ijse.salon.dao.custom.impl;

import lk.ijse.salon.dao.custom.CustomersDAO;
import lk.ijse.salon.entity.Customer;
import lk.ijse.salon.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAOImpl implements CustomersDAO {
    @Override
    public List<Customer> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select * from customers");
        List<Customer> cusList  = new ArrayList<>();

        while (resultSet.next()){
            Customer customerDTO = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            cusList.add(customerDTO);
        }
        return cusList;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Customers values (?,?,?,?,?,?)",
                dto.getC_id(),
                dto.getFirst_name(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone_number(),
                dto.getGender());
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customers SET c_id = ?, first_name = ?,nic = ?,address = ?,phone_number = ?,gender = ?, WHERE c_id = ?",
                dto.getC_id(),
                dto.getFirst_name(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone_number(),
                dto.getGender());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customers WHERE c_id=?" + id);

        if (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Customers where c_id = ?",id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT c_id FROM customers ORDER BY c_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("c_id");
            String numericPart = id.replaceAll("\\D", "");
            int newCustomerId = Integer.parseInt(numericPart) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "C001";
        }
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) from customers");
        int count = 0;
        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;
    }
}
