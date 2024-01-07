package lk.ijse.salon.bo.custom;

import lk.ijse.salon.bo.SuperBO;
import lk.ijse.salon.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeesBO extends SuperBO {
    List<EmployeeDto> loadAllEmployees() throws SQLException,ClassNotFoundException;

    boolean saveEmployees(EmployeeDto dto) throws SQLException,ClassNotFoundException;

    boolean updateEmployees(EmployeeDto dto) throws SQLException,ClassNotFoundException;

    EmployeeDto searchEmployees(String id) throws SQLException,ClassNotFoundException;

    boolean deleteEmployees(String id) throws SQLException,ClassNotFoundException;

    String generateEmployeesId() throws SQLException,ClassNotFoundException;

    boolean loginEmployee(String Username, String Password) throws SQLException,ClassNotFoundException;

    int getEmployeeCount() throws SQLException;
}
