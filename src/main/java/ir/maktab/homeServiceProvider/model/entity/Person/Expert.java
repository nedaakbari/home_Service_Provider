package ir.maktab.homeServiceProvider.model.entity.Person;

import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Expert extends User {
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private double Score;

    @ManyToMany( mappedBy = "experts",fetch = FetchType.EAGER)//چونکه از زیر خدمات اکسپرت رو حذف نمیکرد
    private Set<SubService> subServiceList = new HashSet<>();

    public Expert() {
    }

    @Builder
    public Expert(String name, String lastName, String email, String phoneNumber, String username, String password, UserRegistrationStatus status, Role role) {
        super(name, lastName, email, phoneNumber, username, password, status, role);
    }

    @Override
    public String toString() {
        return "Expert => " + super.toString() + " Score=" + Score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expert expert = (Expert) o;
        return Objects.equals(subServiceList, expert.subServiceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subServiceList);
    }
}
