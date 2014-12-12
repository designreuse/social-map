/*   File Name - UserDao
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
package hms.dao;

import hms.model.User;

import java.util.List;

/**
 * Created by ishara on 12/7/14.
 */

public interface UserDao {
    List<User> findAll();

    Object authenticateUser(String userName, String password);
}
