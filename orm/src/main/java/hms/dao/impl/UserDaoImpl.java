/*   File Name - UserDaoImpl
 *
 *   (C) Copyright 2013-2014 hSenid Software International (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Software International (Pvt) Limited.
 *
 *   hSenid Software International (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 *
 */
package hms.dao.impl;

import hms.dao.UserDao;
import hms.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ishara on 12/7/14.
 */
@Repository("userDao")
public class UserDaoImpl extends UniversalDaoImpl implements UserDao{
    @Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        Session session = getSession();
        List<User> result = session.createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return result;
    }
}
