package ir.maktab.homeServiceProvider.data.dao;


import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.util.requestFilter.UserFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface AdminDao extends PagingAndSortingRepository<Admin, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Admin a SET a.passWord =:passWord WHERE a.id =:id")
    void updatePasswordById(@Param("passWord") String passWord, @Param("id") int id);


    //"From Admin A Where A.passWord = :password and  A.userName=:username"
    Optional<Admin> findByUserNameAndPassWord(String userName, String password);


/*    public List<AdminDto> findAdminsByFilter(UserFilter filter) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Admin.class, "a");

        if (filter.getEmail() != null) {
            SimpleExpression filterByEmail = Restrictions.eq("a.email", filter.getEmail());
            criteria.add(filterByEmail);
        }

        if (filter.getName() != null) {
            SimpleExpression filterByName = Restrictions.eq("a.name", filter.getName());
            criteria.add(filterByName);
        }
        if (filter.getLastName() != null) {
            SimpleExpression filterByFamily = Restrictions.eq("a.lastName", filter.getLastName());
            criteria.add(filterByFamily);
        }

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("a.name").as("name"))
                .add(Projections.property("a.lastName").as("lastName"))
                .add(Projections.property("a.phoneNumber").as("phoneNumber"))
                .add(Projections.property("a.email").as("email"))
                .add(Projections.property("a.id").as("id")));

        criteria.setResultTransformer(Transformers.aliasToBean(AdminDto.class));
        List<AdminDto> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }*/


}
