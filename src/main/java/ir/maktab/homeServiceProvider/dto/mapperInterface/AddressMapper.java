package ir.maktab.homeServiceProvider.dto.mapperInterface;

import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AddressMapper {

    AddressDto addressToDto(Address address);
    Address dtoToaAddress(AddressDto addressDto);

   /* @Override
    public SimpleDestination sourceToDestination(SimpleSource source) {
        if ( source == null ) {
            return null;
        }
        SimpleDestination simpleDestination = new SimpleDestination();
        simpleDestination.setName( source.getName() );
        simpleDestination.setDescription( source.getDescription() );
        return simpleDestination;
    }
    @Override
    public SimpleSource destinationToSource(SimpleDestination destination){
        if ( destination == null ) {
            return null;
        }
        SimpleSource simpleSource = new SimpleSource();
        simpleSource.setName( destination.getName() );
        simpleSource.setDescription( destination.getDescription() );
        return simpleSource;
    }*/
}
