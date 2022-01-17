package ir.maktab.homeServiceProvider.data.model.entity.Person;

import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)//چونکه از زیر خدمات اکسپرت رو حذف نمیکرد//fetch = FetchType.LAZY
    //@EqualsAndHashCode.Include
    @JoinTable(
            joinColumns = {@JoinColumn(name = "expert_id")},
            inverseJoinColumns = {@JoinColumn(name = "subService_id")}
    )
    private Set<SubCategory> subServiceList = new HashSet<>();

    @OneToMany(mappedBy = "expert", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "expert", fetch = FetchType.LAZY)
    private Set<Offer> offerList = new HashSet<>();


    @Override
    public String toString() {
        return "Expert => " + super.toString() + " Score=" + Score;
    }

}
