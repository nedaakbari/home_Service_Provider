package ir.maktab.homeServiceProvider.entity;

import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.enums.ImageType;
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
