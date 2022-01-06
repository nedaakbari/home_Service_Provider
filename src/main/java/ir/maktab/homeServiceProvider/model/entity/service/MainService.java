package ir.maktab.homeServiceProvider.model.entity.service;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MainService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    /*    @Enumerated(EnumType.STRING)
        private MainServices mainServices;*/
    @OneToMany(mappedBy = "main")//,fetch = FetchType.EAGER
    private List<SubService> subServiceList=new ArrayList<>();

    /*@Override
    public String toString() {
        return "MainService{" +
                "id=" + id +
                ", mainServices=" + name +
                '}';
    }*/

    @Override
    public String toString() {
        return "MainService{" +
                "id=" + id +
                ", name='" + name + '\'' +
              //  ", subServiceList=" + subServiceList +
                '}';
    }

    public String print() {
        return "MainService{" +
                "id=" + id +
                ", mainServices=" + name +
                ", subServiceList=" + subServiceList +
                '}';
    }
}
