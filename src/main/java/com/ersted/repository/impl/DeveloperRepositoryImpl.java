package com.ersted.repository.impl;

import com.ersted.model.Developer;
import com.ersted.model.Status;
import com.ersted.repository.DeveloperRepository;
import com.ersted.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Developer create(Developer obj) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        obj.setStatus(Status.ACTIVE);
        obj.setId((Long) session.save(obj));

        transaction.commit();
        session.close();
        System.out.println("INFO: Запись успешно создана.");
        return obj;
    }

    @Override
    public Developer getById(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Developer> query = session.createQuery(
                "FROM Developer dev " +
                        "join fetch dev.specialty spec " +
                        "join fetch dev.skills " +
                        "where dev.id = :id",
                Developer.class)
                .setParameter("id", id);

        Developer developer = query.getSingleResult();

        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public Developer update(Developer obj) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.update(obj);

        transaction.commit();
        session.close();
        System.out.println("INFO: Запись успешно обновлена.");
        return obj;
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Developer developer = session.get(Developer.class, id);

        developer.setStatus(Status.DELETED);
        session.update(developer);

        transaction.commit();
        session.close();
        System.out.println("INFO: Запись успешно удалена.");
    }

    @Override
    public List<Developer> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<Developer> developers = session.createQuery(
                "FROM Developer dev " +
                        "join fetch dev.specialty spec",
                Developer.class)
                .getResultList();

        transaction.commit();
        session.close();
        return developers;
    }
}
