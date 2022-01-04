package ir.maktab.homeServiceProvider.util.filter;

import ir.maktab.homeServiceProvider.model.enumration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ExpertFilter {
    private String name;
    private String family;
    private Date registerDate;
    private Date startDate;
    private Date endDate;
    private int startScore;
    private String mainService;
    private String subService;
    private Role role;

}
