package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.entity.ImageFile;
import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.repository.ImageFileRepository;
import ir.maktab.homeServiceProvider.service.interfaces.ImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
@RequiredArgsConstructor
public class ImageFileServiceImpl implements ImageFileService {

    private final ImageFileRepository imageFileRepository;

    @Override
    public void uploadImageFile(CommonsMultipartFile image, Expert expert) {
        ImageFile imageFile = new ImageFile();
        imageFile.setName(image.getOriginalFilename());
        imageFile.setData(image.getBytes());
        imageFile.setExpert(expert);
        imageFileRepository.save(imageFile);
    }
}
