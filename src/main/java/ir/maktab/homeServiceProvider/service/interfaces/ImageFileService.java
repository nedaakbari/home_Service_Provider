package ir.maktab.homeServiceProvider.service.interfaces;


import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ImageFileService {

    void uploadImageFile(CommonsMultipartFile image, Expert expert);
}
