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
import java.util.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true)
public class Expert extends User implements Comparable<Expert>{
    @Lob
    @Column(columnDefinition = "BLOB", length = 300000)
    private byte[] image;
    private Double score;

    @ManyToMany(fetch = FetchType.EAGER)//چونکه از زیر خدمات اکسپرت رو حذف نمیکرد
    //@EqualsAndHashCode.Include//,mappedBy = "experts"
    @JoinTable(
            joinColumns = {@JoinColumn(name = "expert_id")},
            inverseJoinColumns = {@JoinColumn(name = "subCategory_id")}
    )
    private Set<SubCategory> subCategoryList = new HashSet<>();

   /* @OneToMany(mappedBy = "expert", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "expert", fetch = FetchType.LAZY)
    private Set<Offer> offerList = new HashSet<>();*/


    @Override
    public int compareTo(Expert o) {
        if (this.score == o.score)
            return Double.compare(this.score, o.score);
        else if (this.score < o.score)
            return 1;
        else return -1;
    }

    @Override
    public String toString() {
        return "Expert => " + super.toString() + " Score=" + score;
    }

}
