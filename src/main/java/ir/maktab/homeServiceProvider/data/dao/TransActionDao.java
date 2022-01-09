package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.TransAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransActionDao extends PagingAndSortingRepository<TransAction,Long> {

    //public void save(TransAction transAction) ;

    //public List<TransAction> findAll() ;
}
