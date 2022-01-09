package ir.maktab.homeServiceProvider.data.model.entity.Person;

import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter//@ToString(callSuper = true)
@NoArgsConstructor
public class Customer extends User {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Orders> ordersList = new HashSet<>();
    @OneToMany
    private List<Comment> comments;

    @Builder
    public Customer(String name, String lastName, String email, String phoneNumber, String username, String password, UserRegistrationStatus status, Role role) {
        super(name, lastName, email, phoneNumber, username, password, status, role);
    }

    @Override
    public String toString() {
        return "Customer => " + super.toString();
    }
}
