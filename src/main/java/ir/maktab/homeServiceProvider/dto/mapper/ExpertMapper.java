package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import org.springframework.stereotype.Component;


@Component
public class ExpertMapper {
    public ExpertDto expertDto(Expert expert) {
        return ExpertDto.builder()
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .email(expert.getEmail())
                .status(expert.getStatus())
                .registerDate(expert.getRegisterDate())
                .creditCart(expert.getCreditCart())
                .score(expert.getScore())
                .phoneNumber(expert.getPhoneNumber())
                .build();
    }

}
