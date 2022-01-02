package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Expert extends User{
   // private Blob picture;
    private int Score;
    @ManyToMany(mappedBy = "experts")
    private List<SubService> services = new ArrayList<>();

    @Override
    public String toString() {

        return super.toString() +" Score=" + Score;
    }
}
