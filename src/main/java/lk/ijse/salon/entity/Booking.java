package lk.ijse.salon.entity;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Booking {
    private String b_id;
    private String emp_id;
    private String c_id;
    private String date;
    private String time;
}
