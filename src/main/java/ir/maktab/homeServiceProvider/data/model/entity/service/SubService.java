package ir.maktab.homeServiceProvider.data.model.entity.service;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MainService main;
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
