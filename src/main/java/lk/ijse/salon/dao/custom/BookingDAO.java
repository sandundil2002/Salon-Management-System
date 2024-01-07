package lk.ijse.salon.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.salon.dto.tm.BookingTm;
import lk.ijse.salon.entity.Booking;

import java.sql.SQLException;

public interface BookingDAO extends CrudDAO<Booking> {
    int getBookingCount() throws SQLException,ClassNotFoundException;

    boolean placeOrder(Booking dto, ObservableList<BookingTm> list) throws SQLException,ClassNotFoundException;

    boolean saveDetails(ObservableList<BookingTm> list, Booking dto) throws SQLException,ClassNotFoundException;

}
