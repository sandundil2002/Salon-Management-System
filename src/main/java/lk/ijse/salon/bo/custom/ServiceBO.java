package lk.ijse.salon.bo.custom;

import lk.ijse.salon.bo.SuperBO;
import lk.ijse.salon.dto.ServiceDto;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBO {
    List<ServiceDto> loadAllService() throws SQLException,ClassNotFoundException;

    boolean saveService(ServiceDto dto) throws SQLException,ClassNotFoundException;

    boolean updateService(ServiceDto dto) throws SQLException,ClassNotFoundException;

    ServiceDto searchService(String id) throws SQLException,ClassNotFoundException;

    boolean deleteService(String id) throws SQLException,ClassNotFoundException;

    String generateServiceId() throws SQLException,ClassNotFoundException;
}
