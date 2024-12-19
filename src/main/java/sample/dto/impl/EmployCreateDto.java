package sample.dto.impl;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder

public class EmployCreateDto {
    private Integer id;
    private String birthDate;
    private String number;
    private String password;
    private String repeat_password;
    private Integer activityId;
    private Integer accessPermissionId;
}
