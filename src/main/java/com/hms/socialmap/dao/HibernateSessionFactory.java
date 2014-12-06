package com.hms.socialmap.dao;

import org.hibernate.Session;

/**
 * Created by sadupa on 8/6/14.
 */
public interface HibernateSessionFactory {

    Session getSession();
}
