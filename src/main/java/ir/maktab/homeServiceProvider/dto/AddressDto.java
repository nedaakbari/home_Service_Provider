package ir.maktab.homeServiceProvider.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {//address order offer comment
    private String city;
    private String street;
    private String zipCode;
}
