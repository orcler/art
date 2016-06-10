package com.art.dao.impl;

import org.hibernate.Session;

import com.art.dao.HibernateUtil;
import com.art.dao.IUserDao;
import com.art.schema.UserSchema;

public class UserDaoImpl implements IUserDao{

    @Override
    public UserSchema getUserById(String aUserId) {
	Session session = HibernateUtil.getSession();
	return session.get(UserSchema.class, aUserId);
    }
   
    @Override
    public UserSchema addUser(UserSchema aUser) {
	return null;
    }
    
    public static void main(String[] args) {
	UserSchema user = new UserDaoImpl().getUserById("002");
	System.out.println(user.getName());
    }


}
