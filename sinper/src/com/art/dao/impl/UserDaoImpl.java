package com.art.dao.impl;

import org.hibernate.Session;

import com.art.dao.IUserDao;
import com.art.schema.UserSchema;

public class UserDaoImpl implements IUserDao{

    @Override
    public UserSchema getUserById(Session aSession, String aUserId) {
	return aSession.get(UserSchema.class, aUserId);
    }

    @Override
    public UserSchema addUser(Session aSession, UserSchema aUser) {
	return null;
    }


}
