package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import org.springframework.stereotype.Component;

@Component
public class ExpertMapper {

    public ExpertDto toExpertDto(Expert expert) {
        return ExpertDto.builder()
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .email(expert.getEmail())
                //.status(expert.getStatus())
                .registerDate(expert.getRegisterDate())
                .creditCart(expert.getCreditCart())
                .score(expert.getScore())
                .build();
    }

    public Expert toExpert(ExpertDto expertDto) {
        return Expert.builder()
                .firstName(expertDto.getFirstName())
                .lastName(expertDto.getLastName())
                .username(expertDto.getUsername())
                .password(expertDto.getPassword())
                .email(expertDto.getEmail())
                .build();
    }
}
