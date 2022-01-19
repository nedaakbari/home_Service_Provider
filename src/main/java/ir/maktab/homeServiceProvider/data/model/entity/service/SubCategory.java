package ir.maktab.homeServiceProvider.data.model.entity.service;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "❌❌❌ field of mainService can't be empty ❌❌❌")
    private Category category;

    @Column(unique = true)
    private String title;

    private Double baseAmount;

    private String description;

/*    @ManyToMany(mappedBy = "subCategoryList", fetch = FetchType.LAZY)//,mappedBy = "subServiceList"
 *//*   @JoinTable(
            joinColumns = {@JoinColumn(name = "subCategory_id")},
            inverseJoinColumns = {@JoinColumn(name = "expert_id")}
    )*//*
    private Set<Expert> experts = new HashSet<>();*/


    /*@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private Set<Orders> orders = new HashSet<>();*/


}
