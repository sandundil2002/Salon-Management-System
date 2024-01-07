package lk.ijse.salon.dao.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.salon.dao.custom.BookingDAO;
import lk.ijse.salon.dto.tm.BookingTm;
import lk.ijse.salon.entity.Booking;
import lk.ijse.salon.util.SQLUtil;
import lk.ijse.salon.util.TransactionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public List<Booking> loadAll() throws SQLException{
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM booking");
        List<Booking> bookingList = new ArrayList<>();

        while (resultSet.next()) {
            Booking bookingDTO = new Booking(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            bookingList.add(bookingDTO);
        }
        return bookingList;
    }

    @Override
    public boolean save(Booking dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO booking values (?,?,?,?,?)",
                dto.getB_id(),
                dto.getDate(),
                dto.getTime(),
                dto.getEmp_id(),
                dto.getC_id());
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        List<Booking> bookingList = loadAll();
        if (bookingList.isEmpty())return "B001";

        String id = null;
        for (Booking dto : bookingList) {
            id = dto.getB_id();
        }
        int index = Integer.parseInt(id.split("B00")[1]);
        index++;
        return "B00" + index;
    }

    @Override
    public int getBookingCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) from booking");
        int count = 0;
        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;
    }

    @Override
    public boolean placeOrder(Booking dto, ObservableList<BookingTm> list) throws SQLException,ClassNotFoundException {
        try {
            TransactionUtil.startTransaction();
            if (save(dto) && saveDetails(list, dto)) {
                return true;
            } else {
                TransactionUtil.rollBack();
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } finally {
            TransactionUtil.endTransaction();
        }
        return false;
    }

    @Override
    public boolean saveDetails(ObservableList<BookingTm> list, Booking dto) throws SQLException {
        for (BookingTm tm : list) {
            return SQLUtil.execute("INSERT INTO bookingdetails VALUES (?,?)" ,tm.getSId(),dto.getB_id());
        }
        return false;
    }

    @Override
    public boolean update(Booking dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Booking search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
