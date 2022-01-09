package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.service.MainService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainServiceDao extends PagingAndSortingRepository<MainService, Integer> {

    //void save
    // void delete(MainService mainService)
//List<MainService> findAll()
   /* public void update(MainService mainService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(mainService);
        transaction.commit();
        session.close();
    }*/

    //"From MainService M Where M.name=:name"
    Optional<MainService> findByName(String name);
    //"From MainService M Where M.id=:id"
    Optional<MainService> findById(int mainId);



}
