package ir.maktab.homeServiceProvider.dto.mapperInterface;

import ir.maktab.homeServiceProvider.data.model.entity.TransActions;
import ir.maktab.homeServiceProvider.dto.TransActionDto;
import org.mapstruct.Mapper;

@Mapper
public interface TransActionMapper {
    TransActionDto entityToDto(TransActions transActions);

    TransActions DtoToEntity(TransActionDto transActionDto);
}
