package sample.entity;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class AddressEntity {
    private Integer id;
    private String street;
    private Integer idEmploy;
    private String city;
    private Integer houseNumber;
    private Integer apartmentHumber;


}
