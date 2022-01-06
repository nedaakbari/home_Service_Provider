package ir.maktab.homeServiceProvider.model.dto;

import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubServiceDto {
    private String name;
    private Long basePrice;
    private String description;
    private MainService mainService;

    @Override
    public String toString() {
        return "SubService=>{" +
                "name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", mainService=" + mainService.getName() +
                '}';
    }
}
