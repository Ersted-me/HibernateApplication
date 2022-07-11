package com.ersted.repository.impl;

import com.ersted.model.Skill;
import com.ersted.repository.SkillRepository;
import com.ersted.util.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {

    @Override
    public Skill create(Skill obj) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            obj.setName(obj.getName().toLowerCase());

            Long skillId = (Long) session.save(obj);

            obj.setId(skillId);

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
    public Skill getById(Long id) {
        return HibernateUtil.getSession().get(Skill.class, id);
    }

    @Override
    public Skill update(Skill obj) {
        Session session = null;
        Transaction transaction = null;
        Skill current = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            current = session.load(Skill.class, obj.getId());

            current.setName(obj.getName().toLowerCase());
            session.update(current);
            transaction.commit();
        } catch (ObjectNotFoundException e) {
            if (transaction != null)
                transaction.rollback();

            System.out.println("ERROR: Запись не найдена. Навык: " + obj.toString());
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
        System.out.println("INFO: Запись успешно обновлена.");
        return current;
    }

    @Override
    public void deleteById(Long id) {

        try(Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            Skill skill = new Skill();
            skill.setId(id);
            session.delete(skill);

            transaction.commit();
        }
        System.out.println("INFO: Запись успешно удалена.");
    }

    @Override
    public List<Skill> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<Skill> skills = session.createQuery("FROM com.ersted.model.Skill").getResultList();

        transaction.commit();
        session.close();

        return skills;
    }
}
