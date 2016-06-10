package com.art.service;

import org.hibernate.Session;

import com.art.dao.HibernateUtil;
import com.art.dao.IUserDao;
import com.art.schema.UserSchema;

public class UserService {
    
    private IUserDao  userDao;
    private Session session;
    
    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(String aUserId, String aPwd) {
	session = HibernateUtil.getSession();
	System.out.println(session);
	UserSchema user = userDao.getUserById(session, aUserId);
	session.close();
	if (user != null) {
	    if (aPwd.equals(user.getPwd())) {
		return true;
	    }
	}
	return false;
    }
    
}
