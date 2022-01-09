package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.service.SubService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubServiceDao extends PagingAndSortingRepository<SubService, Integer> {

    //public void save(SubService subService) ;
    //public void delete(SubService subService) ;

    //public List<SubService> findAll() ;

 /*   public void update(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(subService);
        transaction.commit();
        session.close();
    }*/

    @Query(value = "From SubService S JOIN fetch S.main where S.main.id=:id")
    List<SubService> findSubservienceFromMainService(int mainServiceId);

    //"From SubService S where S.name=:name"
    Optional<SubService> findByName(String name);

    //"From SubService S where S.id=:id"
    public Optional<SubService> findById(int id);

    @Query(value = "From SubService S JOIN fetch S.experts E where E.id=:id")
    public List<SubService> findSubserivceOfExpert(int expertId);

 /*   public List<SubService> findByNameCriteria(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(SubService.class, "s");
        criteria.setFetchMode("experts", FetchMode.EAGER);
        criteria.add(Restrictions.eq("s.name", name));
        List<SubService> subServiceList = criteria.list();
        transaction.commit();
        session.close();
        return subServiceList;
    }*/


}
