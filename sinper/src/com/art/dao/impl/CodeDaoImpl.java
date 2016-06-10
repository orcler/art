package com.art.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.art.dao.HibernateUtil;
import com.art.dao.ICodeDao;
import com.art.schema.CodeSchema;

public class CodeDaoImpl implements ICodeDao {

    @Override
    public List<?> query(Session aSession, String aSql) {
	Session session = HibernateUtil.getSession();
	SQLQuery tQuery = session.createSQLQuery(aSql);
    	tQuery.addEntity(CodeSchema.class);
	return tQuery.list();
    }

}
