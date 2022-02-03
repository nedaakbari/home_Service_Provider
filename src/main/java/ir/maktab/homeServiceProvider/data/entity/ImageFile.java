package ir.maktab.homeServiceProvider.data.entity;

import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.enums.ImageType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ImageType type = ImageType.PROFILE;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] data;

    @ManyToOne
    private Expert expert;

}
