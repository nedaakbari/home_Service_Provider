package ir.maktab.homeServiceProvider.model.entity.service;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*@Enumerated(EnumType.STRING)//???????????
    @Column(nullable = false)
    private MainServices mainService;*/

    @ManyToOne
    //@Column(nullable = false)
    private MainService main;

    @Column(unique = true)
    private String name;
    private long baseAmount;
    private String description;
   /* @ManyToMany(fetch = FetchType.EAGER)//(mappedBy = "services")
    private List<Expert> experts = new ArrayList<>();*/


    @Override
    public String toString() {
        return "SubService{" +
                " id=" + id  +" " + main.getName() +
               // ", name='" + name + '\'' +
                ", baseAmount=" + baseAmount +
                ", description='" + description + '\'' +
                '}';
    }

}
