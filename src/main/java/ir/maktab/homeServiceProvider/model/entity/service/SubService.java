package ir.maktab.homeServiceProvider.model.entity.service;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    //@Column(nullable = false)
    private MainService main;

    @Column(unique = true)
    private String name;
    private long baseAmount;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subServiceList")
    private List<Expert> experts = new ArrayList<>();

    /*@Enumerated(EnumType.STRING)//???????????
        @Column(nullable = false)
        private MainServices mainService;*/

    @Override
    public String toString() {
        return "SubService{" +
                " id=" + id + " MainService=> " + main.getName() +
                ", name='" + name + '\'' +
                ", baseAmount=" + baseAmount +
                ", description='" + description + '\'' +
                '}';
    }


}
