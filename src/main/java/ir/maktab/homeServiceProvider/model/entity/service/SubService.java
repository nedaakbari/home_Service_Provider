package ir.maktab.homeServiceProvider.model.entity.service;

import ir.maktab.homeServiceProvider.model.entity.Person.Expert;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)//,mappedBy = "subServiceList"
    private Set<Expert> experts ;

    /*@Enumerated(EnumType.STRING)//???????????
        @Column(nullable = false)
        private MainServices mainService;*/

    @Override
    public String toString() {
        return "SubService{" +
                " id=" + id  +" MainService=> " + main.getName() +
                ", name='" + name + '\'' +
                ", baseAmount=" + baseAmount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubService that = (SubService) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
