package hms.dao.impl;

import hms.dao.UniversalDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

public class UniversalDaoImpl implements UniversalDao {

    @Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    @Override
    public boolean save(Object object) {
        Session session = getSession();
        session.save(object);
        return true;
    }

    @Override
    public boolean update(Object object) {
        Session session = getSession();
        session.update(object);
        return true;
    }

    @Override
    public boolean delete(Object object) {
        Session session = getSession();
        session.delete(object);
        return true;
    }

    public boolean saveOrUpdate(Object object) {
        Session session = getSession();
        session.saveOrUpdate(object);
        return true;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
