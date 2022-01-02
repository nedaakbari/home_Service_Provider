package ir.maktab.homeServiceProvider.model.entity.service;

import ir.maktab.homeServiceProvider.model.entity.Expert;
import ir.maktab.homeServiceProvider.model.enumeration.MainServices;
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
    //@Enumerated(EnumType.STRING)???????????
    private MainServices mainService;
    private String name;
    private long baseAmount;
    private String description;
    @ManyToMany//(mappedBy = "services")
    private List<Expert> experts = new ArrayList<>();
}
