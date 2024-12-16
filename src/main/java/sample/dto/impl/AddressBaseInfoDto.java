package sample.dto.impl;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class AddressBaseInfoDto {
    private Integer id;
    private String street;
    private Integer idEmploy;
    private String city;
    private Integer houseNumber;
    private Integer apartmentHumber;
}
