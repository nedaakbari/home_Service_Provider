package ir.maktab.homeServiceProvider.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long amount;

    @OneToMany
    private List<Admin> admins=new ArrayList<>();
}
