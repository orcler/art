package com.art.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.art.dao.HibernateUtil;
import com.art.dao.SerialNoDao;
import com.art.schema.SerialNoSchema;

public class SerialNoDaoImpl implements SerialNoDao {

    @Override
    public boolean save(SerialNoSchema aSerialNoSchema) {
	Session session = HibernateUtil.getSession();
	Transaction tTransaction;
	tTransaction = session.beginTransaction();
	try {
	    session.save(aSerialNoSchema);
	    tTransaction.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    tTransaction.rollback();
	    return false;
	} finally {
	    session.close();
	}
	return false;
    }

    @Override
    public SerialNoSchema getByType(String aType) {
	String tSql = " select * from serialno where notype='" + aType + "' ";
	SerialNoSchema tSchema = new SerialNoSchema();
	tSchema.setNotype(aType);
	Session session = HibernateUtil.getSession();
	Query query =  session.createSQLQuery(tSql);
	List<SerialNoSchema> tList = query.list();
	if (tList == null || tList.size() ==0)
	{
	    return null;
	}
	return tList.get(1);
    }

}
