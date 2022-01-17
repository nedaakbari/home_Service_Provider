package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertDao extends JpaRepository<Expert, Integer> {

    //void save(Expert expert)
    //void delete(Expert expert)
    // List<Expert> findAll()

    //"From Expert E Where E.email = :email"
    Optional<Expert> findByEmail(String email);

    //"From Expert E Where E.password = :password and  E.username=:username"
    Optional<Expert> findByUsernameAndPassword(String userName, String password);


    @Modifying
    @Query(value = "UPDATE Expert e set e.password =:password where e.id=:id")
    void UpdatePassword(@Param("password") String password, @Param("id") int id);




   /* public List<ExpertDto> findAllExpertsByFilter(UserFilter filter) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Expert.class, "e");
        criteria.createAlias("e.subServiceList", "s");

        if (filter.getName() != null) {
            SimpleExpression filterByName = Restrictions.eq("e.name", filter.getName());
            criteria.add(filterByName);
        }
        if (filter.getLastName() != null) {
            SimpleExpression filterByFamily = Restrictions.eq("e.lastName", filter.getLastName());
            criteria.add(filterByFamily);
        }

        if (filter.getSubService() != null) {
            SimpleExpression filterByMainService = Restrictions.eq("s.name", filter.getSubService());
            criteria.add(filterByMainService);
        }
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("u.registerDate").as("registerDate"))
                .add(Projections.property("u.role").as("role"))
                .add(Projections.property("u.name").as("name"))
                .add(Projections.property("u.lastName").as("lastName"))
                .add(Projections.property("u.role").as("role"))
                .add(Projections.property("u.phoneNumber").as("phoneNumber"))
                .add(Projections.property("u.email").as("email"))
                .add(Projections.property("u.status").as("status"))
                .add(Projections.property("u.id").as("id")));

        criteria.setResultTransformer(Transformers.aliasToBean(ExpertDto.class));
        List<ExpertDto> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }*/

}
