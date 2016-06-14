package com.art.dao;

import org.hibernate.Session;

import com.art.schema.UserSchema;

public interface IUserDao {
    
    public UserSchema getUserById(Session aSession, String aUserId);
   
    public UserSchema addUser(Session aSession, UserSchema aUser);

}
