package ir.maktab.homeServiceProvider.data.model.entity.service;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    //@JoinColumn(nullable = false)
    @NotNull(message = "title cannot be null")
    private Category main;

    @Column(unique = true)
    private String title;

    private Double baseAmount;

    private String description;

    @ManyToMany(mappedBy = "subServiceList",fetch = FetchType.EAGER)//,mappedBy = "subServiceList"
    private Set<Expert> experts=new HashSet<>();

    @OneToMany(mappedBy ="subService", fetch = FetchType.LAZY)
    private Set<Orders> orders=new HashSet<>();;

    @Override
    public String toString() {
        return "SubService{" +
                " id=" + id + " MainService=> " + main +
                ", title='" + title + '\'' +
                ", baseAmount=" + baseAmount +
                ", description='" + description + '\'' +
                '}';
    }

}
