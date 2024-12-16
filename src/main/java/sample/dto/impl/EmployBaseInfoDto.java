package sample.dto.impl;


import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder

public class EmployBaseInfoDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String number;
    private Integer activityId;
    private Integer accessPermissionId;

}
