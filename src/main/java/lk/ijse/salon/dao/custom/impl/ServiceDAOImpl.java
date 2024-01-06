package lk.ijse.salon.dao.custom.impl;

import lk.ijse.salon.dao.custom.ServiceDAO;
import lk.ijse.salon.entity.Service;
import lk.ijse.salon.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public List<Service> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select * from service");
        List<Service> serviceList = new ArrayList<>();

        while (resultSet.next()){
            Service serviceDTO = new Service(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            serviceList.add(serviceDTO);
        }
        return serviceList;
    }

    @Override
    public boolean save(Service dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into service values (?,?,?,?,?)",
                dto.getService_id(),
                dto.getService_name(),
                dto.getPrice(),
                dto.getDescription());
    }

    @Override
    public boolean update(Service dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(" UPDATE service SET Service_Id = ?, price = ?, service_type = ?, Description= ?,Service_Name = ? WHERE Service_Id = ?",
                dto.getService_id(),
                dto.getService_name(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getService_type());
    }

    @Override
    public Service search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select * from service where Service_Id=?",id);

        if (resultSet.next()){
            return new Service(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE  FROM service WHERE Service_Id=?",id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Service_id FROM service ORDER BY Service_id DESC LIMIT 1");
        if(resultSet.next()){
            String id = resultSet.getString("Service_id");
            String numericPart = id.replaceAll("\\D", "");
            int newEmployeeId = Integer.parseInt(numericPart) + 1 ;
            return String.format("S%03d", newEmployeeId);
        } else {
            return "S001";
        }
    }
}
