package lk.ijse.salon.dao.custom;

import lk.ijse.salon.entity.Employee;

import java.sql.SQLException;

public interface EmployeesDAO extends CrudDAO<Employee> {
    boolean loginEmployee(String Username, String Password) throws SQLException,ClassNotFoundException;

    int getEmployeeCount() throws SQLException;
}
