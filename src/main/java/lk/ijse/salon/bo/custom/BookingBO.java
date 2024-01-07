package lk.ijse.salon.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.salon.bo.SuperBO;
import lk.ijse.salon.dto.AppoinmentDto;
import lk.ijse.salon.dto.tm.BookingTm;
import lk.ijse.salon.entity.Booking;

import java.sql.SQLException;
import java.util.List;

public interface BookingBO extends SuperBO {
    List<AppoinmentDto> loadAllBookings() throws SQLException,ClassNotFoundException;

    boolean saveBooking(AppoinmentDto dto) throws SQLException,ClassNotFoundException;

    boolean updateBooking(AppoinmentDto dto) throws SQLException,ClassNotFoundException;

    AppoinmentDto searchBooking(String id) throws SQLException,ClassNotFoundException;

    boolean deleteBooking(String id) throws SQLException,ClassNotFoundException;

    String generateBookingId() throws SQLException,ClassNotFoundException;

    int getBookingCount() throws SQLException,ClassNotFoundException;

    boolean placeOrder(Booking dto, ObservableList<BookingTm> list) throws SQLException,ClassNotFoundException;

    boolean saveDetails(ObservableList<BookingTm> list, Booking dto) throws SQLException,ClassNotFoundException;
}
