package ir.maktab.homeServiceProvider.data.model.entity.service;

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
    @OneToMany(mappedBy = "main", fetch = FetchType.LAZY)
    private List<SubService> subServiceList = new ArrayList<>();

    @Override
    public String toString() {
        return "MainService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
