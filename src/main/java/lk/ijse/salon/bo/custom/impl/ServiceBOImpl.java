package lk.ijse.salon.bo.custom.impl;

import lk.ijse.salon.bo.custom.ServiceBO;
import lk.ijse.salon.dao.DAOFactory;
import lk.ijse.salon.dao.custom.ServiceDAO;
import lk.ijse.salon.dto.ServiceDto;
import lk.ijse.salon.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SERVICE);
    @Override
    public List<ServiceDto> loadAllService() throws SQLException, ClassNotFoundException {
        List<Service> services = serviceDAO.loadAll();
        List<ServiceDto> serviceDtos = new ArrayList<>();

        for (Service service : services){
            serviceDtos.add(new ServiceDto(
                    service.getService_id(),
                    service.getService_name(),
                    service.getService_type(),
                    service.getPrice(),
                    service.getDescription()
            ));
        }
        return serviceDtos;
    }

    @Override
    public boolean saveService(ServiceDto dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.save(new Service(
                dto.getService_id(),
                dto.getService_name(),
                dto.getService_type(),
                dto.getPrice(),
                dto.getDescription()
        ));
    }

    @Override
    public boolean updateService(ServiceDto dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(
                dto.getService_id(),
                dto.getService_name(),
                dto.getService_type(),
                dto.getPrice(),
                dto.getDescription()
        ));
    }

    @Override
    public ServiceDto searchService(String id) throws SQLException, ClassNotFoundException {
        Service service = serviceDAO.search(id);
        ServiceDto serviceDto = new ServiceDto(
                service.getService_id(),
                service.getService_name(),
                service.getService_type(),
                service.getPrice(),
                service.getDescription());
        return serviceDto;
    }

    @Override
    public boolean deleteService(String id) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(id);
    }

    @Override
    public String generateServiceId() throws SQLException, ClassNotFoundException {
        return serviceDAO.generateId();
    }
}
