package lk.ijse.salon.bo;

import lk.ijse.salon.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        BOOKING,CUSTOMER,EMPLOYEE,PRODUCT,SERVICE
    }

    public SuperBO getBO(BOFactory.BOTypes boTypes){
        switch (boTypes){
            case BOOKING  :
                return new BookingBOImpl();
            case CUSTOMER:
                return new CustomersBOImpl();
            case EMPLOYEE:
                return new EmployeesBOImpl();
            case PRODUCT:
                return new ProductsBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            default:
                return null;
        }
    }
}
