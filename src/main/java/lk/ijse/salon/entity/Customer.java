package lk.ijse.salon.entity;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private String c_id;
    private String first_name;
    private String nic;
    private String address;
    private String phone_number;
    private String gender;
}