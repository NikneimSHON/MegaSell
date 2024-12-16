package sample.entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployEntity{
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String number;
    private Integer activityId;
    private Integer accessPermissionId;

}
