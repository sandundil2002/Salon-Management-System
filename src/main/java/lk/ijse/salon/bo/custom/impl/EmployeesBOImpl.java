package lk.ijse.salon.bo.custom.impl;

import lk.ijse.salon.bo.custom.EmployeesBO;
import lk.ijse.salon.dao.DAOFactory;
import lk.ijse.salon.dao.custom.EmployeesDAO;
import lk.ijse.salon.dto.EmployeeDto;
import lk.ijse.salon.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesBOImpl implements EmployeesBO {
    EmployeesDAO employeesDAO = (EmployeesDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public List<EmployeeDto> loadAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeesDAO.loadAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees){
            employeeDtos.add(new EmployeeDto(
                    employee.getEmp_id(),
                    employee.getFirst_name(),
                    employee.getLast_name(),
                    employee.getEmail(),
                    employee.getPhone_number(),
                    employee.getNic(),
                    employee.getJob_rank(),
                    employee.getUsername(),
                    employee.getPassword()));
        }
        return employeeDtos;
    }

    @Override
    public boolean saveEmployees(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeesDAO.save(new Employee(
                dto.getEmp_id(),
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getEmail(),
                dto.getPhone_number(),
                dto.getNic(),
                dto.getJob_rank(),
                dto.getUsername(),
                dto.getPassword()));
    }

    @Override
    public boolean updateEmployees(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeesDAO.update(new Employee(
                dto.getEmp_id(),
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getEmail(),
                dto.getPhone_number(),
                dto.getNic(),
                dto.getJob_rank(),
                dto.getUsername(),
                dto.getPassword()));
    }

    @Override
    public EmployeeDto searchEmployees(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeesDAO.search(id);
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getEmp_id(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getEmail(),
                employee.getPhone_number(),
                employee.getNic(),
                employee.getJob_rank(),
                employee.getUsername(),
                employee.getPassword());
        return employeeDto;
    }

    @Override
    public boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException {
        return employeesDAO.delete(id);
    }

    @Override
    public String generateEmployeesId() throws SQLException, ClassNotFoundException {
        return employeesDAO.generateId();
    }

    @Override
    public boolean loginEmployee(String Username, String Password) throws SQLException, ClassNotFoundException {
        return employeesDAO.loginEmployee(Username, Password);
    }

    @Override
    public int getEmployeeCount() throws SQLException {
        return employeesDAO.getEmployeeCount();
    }
}
