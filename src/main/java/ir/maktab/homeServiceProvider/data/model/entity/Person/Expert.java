package ir.maktab.homeServiceProvider.data.model.entity.Person;

import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true)
public class Expert extends User {
    @Lob
    @Column(columnDefinition = "BLOB", length = 300000)
    private byte[] image;
    private Double Score;
    @ManyToMany(mappedBy = "experts", fetch = FetchType.EAGER)//چونکه از زیر خدمات اکسپرت رو حذف نمیکرد
    //@EqualsAndHashCode.Include
    private Set<SubService> subServiceList = new HashSet<>();

    @OneToMany(mappedBy = "expert",fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "expert",fetch = FetchType.LAZY)
    private Set<Offer> offerList = new HashSet<>();


    @Override
    public String toString() {
        return "Expert => " + super.toString() + " Score=" + Score;
    }

}
