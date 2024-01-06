package lk.ijse.salon.entity;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Employee {
    private String emp_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String nic;
    private String job_rank;
    private String username;
    private String password;

    public Employee(String userName, String password) {
        this.first_name = userName;
        this.password = password;
    }
}
