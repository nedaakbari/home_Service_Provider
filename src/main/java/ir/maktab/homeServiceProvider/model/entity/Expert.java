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

   /* @ElementCollection
    @CollectionTable
    @MapKeyColumn(name = "order_id")//  اسم ستون متعلق به کی
    @Column(name = "score_value")//اسم متعلق به ولیو
    private Map<Orders, Integer> score = new HashMap<>();*/

    @ManyToMany(mappedBy = "experts", fetch = FetchType.EAGER)
    private List<MainService> mainServices = new ArrayList<>();

    /*public Expert() {
        saveImage();
    }*/

    @Override
    public String toString() {
        return super.toString() + " Score=" + Score;
    }

/*    @Override
    public String toString() {
        return super.toString()+
                "Score=" + Score +
                ", services=" + services;
    }*/

    /*@PreRemove
    public void removeExpertFromSubservice() {
        for (SubService s : services) {
            s.getExperts().remove(this);
        }
    }*/

  /*  public  void saveImage() {
        File file = new File("C:\\image\\" + this.getName() + "");
        byte[] imageData = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        this.setImage(imageData);
    }*/
}
