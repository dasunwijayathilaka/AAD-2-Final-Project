package lk.ijse.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dasun Wijayathilaka
 * @created 6/20/2025
 * @github https://github.com/dasunwijayathilaka
 * @project Smart-Parking-Management-System-Microservice-Based-Application
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String userType;
}