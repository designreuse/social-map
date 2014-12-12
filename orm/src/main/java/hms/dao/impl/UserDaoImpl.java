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
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by ishara on 12/7/14.
 */
@Repository("userDao")
public class UserDaoImpl extends UniversalDaoImpl implements UserDao {
    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        Session session = getSession();
        List<User> result = session.createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return result;
    }

    @Override
    public Object authenticateUser(String userName, String password) {
        Session session = getSession();
        String md5password = getMD5String(password);
        if (md5password != null) {
            Object result = session.createCriteria(User.class)
                    .add(Restrictions.eq("name", userName))
                    .add(Restrictions.eq("password", md5password))
                    .uniqueResult();
            return result;
        } else {
            return null;
        }

    }

    private String getMD5String(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }
}
