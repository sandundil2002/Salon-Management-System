package lk.ijse.salon.model;

import javafx.collections.ObservableList;
import lk.ijse.salon.db.DbConnection;
import lk.ijse.salon.dto.AppoinmentDto;
import lk.ijse.salon.dto.tm.BookingTm;
import lk.ijse.salon.util.SQLUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentModel {
    public static boolean plaseOrder(AppoinmentDto dto, ObservableList<BookingTm> list) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (save(dto) && saveDetails(list, dto)) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    private static boolean saveDetails(ObservableList<BookingTm> list, AppoinmentDto dto) throws SQLException {
        for (BookingTm tm : list) {
            return SQLUtil.execute("INSERT INTO bookingdetails VALUES (?,?)" ,tm.getSId(),dto.getB_id());
        }
        return false;
    }

    private static boolean save(AppoinmentDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO booking values (?,?,?,?,?)",dto.getB_id(),dto.getDate(),dto.getTime(),dto.getEmp_id(),dto.getC_id());
    }

    public static String getNext() throws SQLException {
        List<AppoinmentDto> appoinmentDtos = loadAllShedules();
        if (appoinmentDtos.isEmpty())return "B001";

        String id = null;
        for (AppoinmentDto dto : appoinmentDtos) {
            id = dto.getB_id();
        }
        int index = Integer.parseInt(id.split("B00")[1]);
        index++;
        return "B00" + index;
    }

    public static List<AppoinmentDto> loadAllShedules() throws SQLException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM booking");
        List<AppoinmentDto> ScheduleList = new ArrayList<>();

        while (resultSet.next()) {
            ScheduleList.add(new AppoinmentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));

        }
        return null;
    }

    public int getAllAppoinemts() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) from booking");
        int count = 0;
        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;
    }
}
