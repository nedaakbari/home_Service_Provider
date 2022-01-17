package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface Services<T,G, ID> {
    public void save(T t);
    public void delete(T t);
    public List<G> getAll();
    public T getById(ID theId);
}
