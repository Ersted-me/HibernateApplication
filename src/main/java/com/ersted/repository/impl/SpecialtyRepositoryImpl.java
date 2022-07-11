package com.ersted.repository.impl;

import com.ersted.model.Specialty;
import com.ersted.repository.SpecialtyRepository;
import com.ersted.util.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import java.util.List;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    @Override
    public Specialty create(Specialty obj) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            obj.setName(obj.getName().toLowerCase());

            Long specialtyId = (Long) session.save(obj);

            obj.setId(specialtyId);

            transaction.commit();

        } catch (ConstraintViolationException e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println("ERROR: Запись уже существует.");
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        System.out.println("INFO: Запись успешно добавлена.");
        return obj;
    }

    @Override
    public Specialty getById(Long id) {
        return HibernateUtil.getSession().get(Specialty.class, id);
    }

    @Override
    public Specialty update(Specialty obj) {
        Session session = null;
        Transaction transaction = null;
        Specialty current = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            current = session.load(Specialty.class, obj.getId());

            current.setName(obj.getName().toLowerCase());
            session.update(current);
            transaction.commit();
        } catch (ObjectNotFoundException e) {
            if (transaction != null)
                transaction.rollback();

            System.out.println("ERROR: Запись не найдена. Запись: " + obj.toString());
            return null;
        }catch (PersistenceException e){
            if (transaction != null)
                transaction.rollback();

            System.out.println("ERROR: Запись с таким именем уже существует.");
            return null;
        }finally {
            if (session != null && session.isOpen())
                session.close();
        }
        System.out.println("INFO: Запись успешно обновлен.");
        return current;
    }

    @Override
    public void deleteById(Long id) {

        try(Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            Specialty specialty = session.get(Specialty.class, id);

            if (specialty != null)
                session.delete(specialty);

            transaction.commit();
        }
        System.out.println("INFO: Запись успешно удалена.");
    }

    @Override
    public List<Specialty> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<Specialty> specialties = session.createQuery("FROM com.ersted.model.Specialty").getResultList();

        transaction.commit();
        session.close();

        return specialties;
    }
}
