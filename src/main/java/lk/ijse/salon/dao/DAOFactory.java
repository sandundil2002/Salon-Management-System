package lk.ijse.salon.dao;

import lk.ijse.salon.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
            BOOKING,CUSTOMER,EMPLOYEE,PRODUCT,SERVICE
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case BOOKING  :
                return new BookingDAOImpl();
            case CUSTOMER:
                return new CustomersDAOImpl();
            case EMPLOYEE:
                return new EmployeesDAOImpl();
            case PRODUCT:
                return new ProductsDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            default:
                return null;
        }
    }
}
