package ir.maktab.homeServiceProvider.dto.mapperInterface;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import org.mapstruct.Mapper;

@Mapper//(componentModel = "spring")
public interface ExpertMapper {
    /* @Autowired
     protected SimpleService simpleService;*/

    ExpertDto expertToExpertDto(Expert expert);

    Expert expertDtoToExpert(ExpertDto expertDto);
}