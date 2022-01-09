package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.model.entity.TransAction;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransActionDao extends PagingAndSortingRepository<TransAction,Long> {

    //public void save(TransAction transAction) ;

    //public List<TransAction> findAll() ;
}
