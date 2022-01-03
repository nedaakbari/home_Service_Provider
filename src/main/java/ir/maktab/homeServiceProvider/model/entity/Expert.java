package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Expert extends User{
    //private Blob picture;
    private byte[] image;

    private int Score;

    @ManyToMany(mappedBy = "experts", fetch = FetchType.EAGER)
    private List<MainService> mainServices = new ArrayList<>();


    @Override
    public String toString() {
        return super.toString() + " Score=" + Score;
    }

}
