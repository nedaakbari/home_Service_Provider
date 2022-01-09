package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.TransActions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransActionDao extends PagingAndSortingRepository<TransActions,Long> {

    //public void save(TransAction transAction) ;

    //public List<TransAction> findAll() ;
}
