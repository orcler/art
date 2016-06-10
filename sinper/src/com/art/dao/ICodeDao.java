package com.art.dao;

import java.util.List;

import org.hibernate.Session;

public interface ICodeDao {
    
    public List<?> query(Session aSession, String aSql);

}
