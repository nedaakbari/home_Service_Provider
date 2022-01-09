package ir.maktab.homeServiceProvider.data.model.entity.Person;

import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
//@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Orders> ordersList = new HashSet<>();
    @OneToMany
    private List<Comment> comments=new ArrayList<>();

    @Override
    public String toString() {
        return "Customer => " + super.toString();
    }
}
