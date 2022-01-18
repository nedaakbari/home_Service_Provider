/*
package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper//(componentModel = "spring")
@Component
public interface ExpertMapper {
    */
/* @Autowired
     protected SimpleService simpleService;*//*


    ExpertDto entityToDto(Expert expert);

    Expert dtoToEntity(ExpertDto expertDto);
}
@Component
class ExpertMapperImpl implements ExpertMapper {

    @Override
    public ExpertDto entityToDto(Expert expert) {
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

    @Override
    public Expert dtoToEntity(ExpertDto expertDto) {
        return null;
    }
}
*/
