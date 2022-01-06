package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class BookDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public Book saveBook(Book book)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return book;
    }

    public Book getBook(Long bookId)
    {
        Session session = sessionFactory.openSession();
        try {
            Book book = (Book) session.get(Book.class, bookId);
            return book;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}