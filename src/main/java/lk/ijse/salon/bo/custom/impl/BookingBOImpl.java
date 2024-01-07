package lk.ijse.salon.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.salon.bo.custom.BookingBO;
import lk.ijse.salon.dao.DAOFactory;
import lk.ijse.salon.dao.custom.BookingDAO;
import lk.ijse.salon.dto.AppoinmentDto;
import lk.ijse.salon.dto.tm.BookingTm;
import lk.ijse.salon.entity.Booking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingBOImpl implements BookingBO {
    BookingDAO bookingDAO = (BookingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOKING);
    @Override
    public List<AppoinmentDto> loadAllBookings() throws SQLException, ClassNotFoundException {
        List<Booking> bookingList = bookingDAO.loadAll();
        List<AppoinmentDto> appoinmentDtos = new ArrayList<>();

        for (Booking booking : bookingList){
            appoinmentDtos.add(new AppoinmentDto(
                    booking.getB_id(),
                    booking.getDate(),
                    booking.getTime(),
                    booking.getEmp_id(),
                    booking.getC_id()));
        }
        return appoinmentDtos;
    }

    @Override
    public boolean saveBooking(AppoinmentDto dto) throws SQLException, ClassNotFoundException {
        return bookingDAO.save(new Booking(
                dto.getB_id(),
                dto.getDate(),
                dto.getTime(),
                dto.getEmp_id(),
                dto.getC_id()));
    }

    @Override
    public boolean updateBooking(AppoinmentDto dto) throws SQLException, ClassNotFoundException {
        return bookingDAO.update(new Booking(
                dto.getB_id(),
                dto.getDate(),
                dto.getTime(),
                dto.getEmp_id(),
                dto.getC_id()));
    }

    @Override
    public AppoinmentDto searchBooking(String id) throws SQLException, ClassNotFoundException {
        Booking booking = bookingDAO.search(id);
        AppoinmentDto appoinmentDto = new AppoinmentDto(
                booking.getB_id(),
                booking.getDate(),
                booking.getTime(),
                booking.getEmp_id(),
                booking.getC_id());
        return appoinmentDto;
    }

    @Override
    public boolean deleteBooking(String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.delete(id);
    }

    @Override
    public String generateBookingId() throws SQLException, ClassNotFoundException {
        return bookingDAO.generateId();
    }

    @Override
    public int getBookingCount() throws SQLException, ClassNotFoundException {
        return bookingDAO.getBookingCount();
    }

    @Override
    public boolean placeOrder(Booking dto, ObservableList<BookingTm> list) throws SQLException, ClassNotFoundException {
        return bookingDAO.placeOrder(dto,list);
    }

    @Override
    public boolean saveDetails(ObservableList<BookingTm> list, Booking dto) throws SQLException, ClassNotFoundException {
        return bookingDAO.saveDetails(list,dto);
    }
}
