package ir.maktab.homeServiceProvider.model.entity.Person;

import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter//@ToString(callSuper = true)
public class Customer extends User {
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    private Set<Orders> ordersList = new HashSet<>();

    @Builder
    public Customer(String name, String lastName, String email, String phoneNumber, String username, String password, UserRegistrationStatus status, Role role) {
        super(name, lastName, email, phoneNumber, username, password, status, role);
    }

    public Customer() {
        super();
    }

    @Override
    public String toString() {
        return "Customer => " + super.toString();
    }
}
