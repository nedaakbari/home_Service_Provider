package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;

import java.util.List;

public interface Services<T,G, ID> {
    public void save(T t);
    public void delete(T t);
    public List<G> getAll();
    public T getById(ID theId);
}
