package com.art.service;

import com.art.dao.IUserDao;
import com.art.schema.UserSchema;

public class UserService {

    private IUserDao  userDao;
    
    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(String aUserId, String aPwd) {
	UserSchema user = userDao.getUserById(aUserId);
	if (user != null) {
	    if (aPwd.equals(user.getPwd())) {
		return true;
	    }
	}
	return false;
    }
    
}
