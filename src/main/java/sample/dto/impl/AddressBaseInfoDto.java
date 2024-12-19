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
    private String city;
    private String idEmploy;
    private String houseNumber;
    private String apartmentNumber;
}
