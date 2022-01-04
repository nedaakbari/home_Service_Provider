package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author: neda akbari
 */
@Entity
@Data
public class Expert extends User {
    //private Blob picture;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private int Score;

    @ManyToMany(fetch = FetchType.LAZY)//mappedBy = "experts",
    private List<SubService> subServiceList = new ArrayList<>();


    @Override
    public String toString() {
        return super.toString() + " Score=" + Score;
    }

}
