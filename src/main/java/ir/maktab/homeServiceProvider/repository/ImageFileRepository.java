package ir.maktab.homeServiceProvider.repository;

import ir.maktab.homeServiceProvider.entity.ImageFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFileRepository extends CrudRepository<ImageFile, Long> {
}
