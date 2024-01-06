package lk.ijse.salon.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.salon.dao.custom.EmployeesDAO;
import lk.ijse.salon.entity.Employee;
import lk.ijse.salon.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAOImpl implements EmployeesDAO {
    @Override
    public List<Employee> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        List<Employee> empDTO = new ArrayList<>();
        while (resultSet.next()) {
            Employee empList = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9));
            empDTO.add(empList);
        }
        return empDTO;
    }

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into employee values (?,?,?,?,?,?,?,?,?)",
                dto.getEmp_id(),
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getEmail(),
                dto.getPhone_number(),
                dto.getNic(),
                dto.getJob_rank(),
                dto.getUsername(),
                dto.getPassword());
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET first_name = ?, last_name = ?, email = ?, phone_number = ?, nic = ?, job_Rank = ?, username =?, password = ? WHERE emp_id = ?",
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getEmail(),
                dto.getPhone_number(),
                dto.getNic(),
                dto.getJob_rank(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmp_id());
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("Select * from employee where emp_id = ?",id);

        if (resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from employee where emp_id = ?",id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Emp_id FROM employee ORDER BY Emp_id DESC LIMIT 1");
        if(resultSet.next()){
            String id = resultSet.getString("Emp_id");
            String numericPart = id.replaceAll("\\D", "");
            int newEmployeeId = Integer.parseInt(numericPart) + 1 ;
            return String.format("E%03d", newEmployeeId);
        } else {
            return "E001";
        }
    }

    @Override
    public boolean loginEmployee(String Username, String Password) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT username, password FROM employee WHERE username = ?",Username);
        if (resultSet.next()) {
            if (Username.equals(resultSet.getString(1))) {
                if (Password.equals(resultSet.getString(2))) {
                    return true;
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "password is not correct").show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "username is not correct").show();
            }
        }
        return false;
    }

    @Override
    public int getEmployeeCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) from employee");
        int count = 0;
        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;
    }
}
