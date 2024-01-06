package lk.ijse.salon.model;

import lk.ijse.salon.dto.ServiceDto;
import lk.ijse.salon.util.SQLUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel {
    public static List<ServiceDto> loadAllService() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from service");
        List<ServiceDto> serviceList = new ArrayList<>();

        while (resultSet.next()){
            serviceList.add(new ServiceDto(
               resultSet.getString(1),
               resultSet.getString(2),
               resultSet.getString(3),
               resultSet.getString(4),
               resultSet.getString(5)
            ));
        }
        return serviceList;
    }

    public static List<ServiceDto> searchService () throws SQLException {
        ResultSet resultSet = SQLUtil.execute( "select * from service");
        List<ServiceDto> serviceList = new ArrayList<>();

        while (resultSet.next()){
            serviceList.add(new ServiceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return serviceList;
    }

    public static ServiceDto findServesById(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from service where Service_Id=?",id);

        ServiceDto serviceDto =null;
        if (resultSet.next()){
             serviceDto = new ServiceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return serviceDto;
    }

    public boolean updateService(ServiceDto dto) throws SQLException {
        return SQLUtil.execute(" UPDATE service SET Service_Id = ?, price = ?, service_type = ?, Description= ?,Service_Name = ? WHERE Service_Id = ?",
                dto.getService_id(),
                dto.getService_name(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getService_type());
    }

    public boolean saveService(ServiceDto dto) throws SQLException {
        return SQLUtil.execute("insert into service values (?,?,?,?,?)",
                dto.getService_id(),
                dto.getService_name(),
                dto.getPrice(),
                dto.getDescription());
    }

    public boolean deleteService(final String service_ID) throws SQLException {
        return SQLUtil.execute("DELETE  FROM service WHERE Service_Id=?"+service_ID);
    }
}
