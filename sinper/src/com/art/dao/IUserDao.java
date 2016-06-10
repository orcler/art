package com.art.dao;

import com.art.schema.UserSchema;

public interface IUserDao {
    
    public UserSchema getUserById(String aUserId);
   
    public UserSchema addUser(UserSchema aUser);

}
