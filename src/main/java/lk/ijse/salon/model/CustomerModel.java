package lk.ijse.salon.model;

import lk.ijse.salon.dto.CustomerDto;
import lk.ijse.salon.util.SQLUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static List<CustomerDto> loadAllCustomer() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from customers");
        List<CustomerDto> cusList  = new ArrayList<>();

        while (resultSet.next()){
            cusList.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return cusList;
    }

    public static CustomerDto searchCustomer(String id) throws SQLException {
       ResultSet resultSet = SQLUtil.execute("SELECT * FROM customers WHERE c_id=?" + id);

        if (resultSet.next()){
            return new CustomerDto(
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

    public int getCustomerCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) from customers");
        int count = 0;
        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;
    }
    public boolean deleteCustomer(String c_id) throws SQLException {
        return SQLUtil.execute("delete from Customers where c_id = ?",c_id);
    }

    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return SQLUtil.execute("insert into Customers values (?,?,?,?,?,?)",
                dto.getC_id(),
                dto.getFirst_name(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone_number(),
                dto.getGender());
    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE customers SET c_id = ?, first_name = ?,nic = ?,address = ?,phone_number = ?,gender = ?, WHERE c_id = ?",
                dto.getC_id(),
                dto.getFirst_name(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone_number(),
                dto.getGender());
    }
}
