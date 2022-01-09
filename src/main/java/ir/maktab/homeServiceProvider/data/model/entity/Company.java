package ir.maktab.homeServiceProvider.data.model.entity;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long amount;

    @OneToMany
    private Set<Admin> admins = new HashSet<>();
    @OneToMany
    private List<TransAction> transActions = new ArrayList<>();
}
