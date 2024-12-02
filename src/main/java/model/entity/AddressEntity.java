package model.entity;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class AddressEntity {
    private Long id;
    private String street;
    private Long idEmploy;
    private String city;
    private Long houseNumber;
    private Long apartmentHumber;



}
