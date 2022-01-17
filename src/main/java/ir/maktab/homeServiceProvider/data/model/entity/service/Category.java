package ir.maktab.homeServiceProvider.data.model.entity.service;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotNull(message = "title cannot be null")
    private String title;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<SubCategory> subServiceList = new HashSet<>();

    @Override
    public String toString() {
        return "MainService{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}
