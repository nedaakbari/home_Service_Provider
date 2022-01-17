package ir.maktab.homeServiceProvider.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {
    private int id;
    private String city;
    private String street;
    private String zipCode;
}
