package lk.ijse.salon.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.salon.bo.custom.BookingBO;
import lk.ijse.salon.bo.custom.CustomersBO;
import lk.ijse.salon.bo.custom.EmployeesBO;
import lk.ijse.salon.bo.custom.impl.BookingBOImpl;
import lk.ijse.salon.bo.custom.impl.CustomersBOImpl;
import lk.ijse.salon.bo.custom.impl.EmployeesBOImpl;

import java.sql.SQLException;

public class DashboardFormController {
    public PieChart pieChart;
    @FXML
    private ImageView imgDashImage;

    @FXML
    private Label lblemployeecount;

    @FXML
    private Label labTotalAppoinemnts;

    @FXML
    private Label lblcustomercount;

    CustomersBO customersBO = new CustomersBOImpl();
    BookingBO bookingBO = new BookingBOImpl();
    EmployeesBO employeesBO = new EmployeesBOImpl();

    public void initialize(){

        try {
            int i = employeesBO.getEmployeeCount();
            labTotalAppoinemnts.setText(String.valueOf(i));
        } catch (SQLException e) {
            System.out.printf("", e.getMessage());

        }
        try {
            int i = bookingBO.getBookingCount();
            lblemployeecount.setText(String.valueOf(i));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("", e.getMessage());

        }
        try {
            int i = customersBO.getCustomerCount();
            lblcustomercount.setText(String.valueOf(i));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("", e.getMessage());

        }

        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(
                new PieChart.Data("Nail Art", 10),
                new PieChart.Data("Colouring", 20),
                new PieChart.Data("Terapy", 30),
                new PieChart.Data("Haircut", 40),
                new PieChart.Data("Dressing", 50),
                new PieChart.Data("Makeup", 100)

        );
        pieChart.setData(observableList);
        new Thread(){
            @Override
            public void run() {
                try {
                    while (true) {
                        imgDashImage.setImage(new Image("/asstes/Leonardo_Diffusion_XL_salon_working_1.jpg"));
                        sleep(2000);
                        imgDashImage.setImage(new Image("/asstes/Leonardo_Diffusion_XL_salon_working_2.jpg"));
                        sleep(2000);
                        imgDashImage.setImage(new Image("/asstes/Leonardo_Diffusion_XL_salon_employees_2.jpg"));
                        sleep(2000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
    }

}
